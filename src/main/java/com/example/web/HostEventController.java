package com.example.web;

import com.example.exceptions.*;
import com.example.model.*;
import com.example.model.Enumerations.Role;
import com.example.model.Enumerations.Status;
import com.example.repository.LocationRepository;
import com.example.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HostEventController {

    private final EventService eventService;
    private final LocationRepository locationRepository;
    private final ClientService clientService;
    private final BandService bandService;
    private final CateringService cateringService;
    private final PhotographerService photographerService;
    private final UserService userService;
    private final LocationService locationService;

    private final AdminService adminService;


    public HostEventController(EventService eventService, LocationRepository locationRepository,
                               ClientService clientService, BandService bandService,
                               CateringService cateringService, PhotographerService photographerService,
                               UserService userService, LocationService locationService, AdminService adminService) {
        this.eventService = eventService;
        this.locationRepository = locationRepository;
        this.clientService = clientService;
        this.bandService = bandService;
        this.cateringService = cateringService;
        this.photographerService = photographerService;
        this.userService = userService;
        this.locationService = locationService;
        this.adminService = adminService;
    }

    @GetMapping("/host_event")
    public String getHostEventPage(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("bands", bandService.findAllActiveBands());
        model.addAttribute("caterings", cateringService.findAllActiveCaterings());
        model.addAttribute("photographers", photographerService.findAllActivePhotographers());
        model.addAttribute("content", "host_event");

        return "main";
    }

    @PostMapping("/host_event")
    public String handleHostEvent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                  @RequestParam String location,
                                  @RequestParam String type,
                                  @RequestParam(required = false) String description,
                                  @RequestParam List<Band> bands,
                                  @RequestParam List<Catering> caterings,
                                  @RequestParam List<Photographer> photographers,
                                  HttpServletRequest req) {
        try {
            Location location2 = locationRepository.findByName(location);
            User user = userService.findByUsername((String) req.getSession().getAttribute("username")).get();
            Optional<Client> optionalClient = clientService.findById(user.getId());
            Client client;
            if (optionalClient.isPresent()) {
                client = optionalClient.get();
            } else {
                throw new UserNotFoundException("Client not found with ID: " + user.getId());
            }

            Admin admin = adminService.findByUsername("admin");

//            Optional<User> optionalUser = userService.findByUsername("admin");
//            Admin admin;
//            if (optionalUser.isPresent()) {
//                User u = optionalUser.get();
//                if (u.getRole().equals(Role.ROLE_ADMIN)) {
//                    admin = (Admin) u;
//                } else {
//                    throw new ClassCastException("The user found is not an admin");
//                }
//            } else {
//                throw new UsernameNotFoundException("Admin user not found");
//            }

            List<String> bandList = new ArrayList<>();
            if (bands != null) {
                for (Band b : bands) {
                    bandService.findById(b.getId()).ifPresent(band -> bandList.add(b.getId()));
                }
            }

            List<String> cateringList = new ArrayList<>();
            if (caterings != null) {
                for (Catering c : caterings) {
                    cateringService.findById(c.getId()).ifPresent(catering -> cateringList.add(c.getId()));
                }
            }

            List<String> photographerList = new ArrayList<>();
            if (photographers != null) {
                for (Photographer p : photographers) {
                    photographerService.findById(p.getId()).ifPresent(photographer -> photographerList.add(p.getId()));
                }
            }

            Event savedEvent = this.eventService.save(new Event(String.valueOf(time), date, location2.getId(), type, description,
                    client.getId(), bandList, cateringList, photographerList, admin.getId(), Status.CREATED));

            savedEvent.setBandIds(bandList);
            savedEvent.setPhotographerIds(photographerList);
            savedEvent.setCateringIds(cateringList);
            eventService.save(savedEvent); //persisting update lists

            //adding eventId to the Band entity
            for (String bandId : bandList) {
                Band b = this.bandService.findById(bandId).
                        orElseThrow(() -> new BandNotFoundException("Band with %s id not found", bandId));
                b.getEventIds().add(savedEvent.getId());
                bandService.save(b);
                userService.save(b);
            }

            //adding eventId to the Catering entity
            for (String cateringId : cateringList) {
                Catering c = this.cateringService.findById(cateringId).
                        orElseThrow(() -> new CateringNotFoundException("Band with %s id not found", cateringId));
                c.getEventIds().add(savedEvent.getId());
                cateringService.save(c);
                userService.save(c);
            }

            //adding eventId to the Photographer entity
            for (String photographerId : photographerList) {
                Photographer p = this.photographerService.findById(photographerId).
                        orElseThrow(() -> new PhotographerNotFoundException("Band with %s id not found", photographerId));
                p.getEventIds().add(savedEvent.getId());
                photographerService.save(p);
                userService.save(p);
            }

            admin.setNumberEvents(admin.getNumberEvents() + 1);
            admin.getEventIds().add(savedEvent.getId());
            adminService.save(admin); //persisting update to db
            userService.save(admin);

            client.setNumberEvents(client.getNumberEvents() + 1);
            client.getEventIds().add(savedEvent.getId());
            clientService.save(client); //persisting update to db
            userService.save(client);

            return "redirect:/home?eventSuccess=true";
        } catch (Exception e) {
            return "redirect:/?error=" + e.getMessage();
        }
    }

    @GetMapping("/my_events")
    public String getEventsForUser(HttpServletRequest req, Model model) {
        User user = userService.findByUsername((String) req.getSession().getAttribute("username"))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Event> events;

        try {
            if (user.getRole() == Role.ROLE_CLIENT) {
                Client client = clientService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = clientService.getEventsByUser(client);
            } else if (user.getRole() == Role.ROLE_CATERING) {
                Catering catering = cateringService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = cateringService.getEventsByUser(catering);
            } else if (user.getRole() == Role.ROLE_BAND) {
                Band band = bandService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = bandService.getEventsByUser(band);
            } else if (user.getRole() == Role.ROLE_PHOTOGRAPHER) {
                Photographer photographer = photographerService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = photographerService.getEventsByUser(photographer);
            } else {
                throw new NoSuchIDException(user.getId());
            }
        } catch (NoSuchIDException exception) {
            events = eventService.findAll();
        }

        model.addAttribute("events", events);
        model.addAttribute("content", "my_events");
        return "main";
    }


    @GetMapping("/event/{id}")
    public String getEventInfo(@PathVariable String id, Model model) {
        Event event = eventService.findById(id);

        List<String> bandList = new ArrayList<>();
        if (event.getBandIds() != null) {
            for (String bandId : event.getBandIds()) {
                bandService.findById(bandId).ifPresent(band -> bandList.add(band.getName()));
            }
        }
        List<String> cateringList = new ArrayList<>();
        if (event.getCateringIds() != null) {
            for (String cateringId : event.getCateringIds()) {
                cateringService.findById(cateringId).ifPresent(catering -> cateringList.add(catering.getName()));
            }
        }

        List<String> photographerList = new ArrayList<>();
        if (event.getPhotographerIds() != null) {
            for (String photographerId : event.getPhotographerIds()) {
                photographerService.findById(photographerId).ifPresent(photographer -> photographerList.add(photographer.getName()));
            }
        }
        model.addAttribute("event", event);
        model.addAttribute("client", clientService.findById(event.getClientId()).get().getName());
        model.addAttribute("location", locationService.findById(event.getLocationId()).get().getName());
        model.addAttribute("bandList", bandList);
        model.addAttribute("cateringList", cateringList);
        model.addAttribute("photographerList", photographerList);
        model.addAttribute("content", "more_details");
        return "main";
    }

    @PostMapping("/approveEvent/{id}")
    public String approveEvent(@PathVariable String id) {
        Event event = eventService.findById(id);
        event.setStatus(Status.APPROVED);
        eventService.update(event);
        return "redirect:/my_events";
    }

    @PostMapping("/rejectEvent/{id}")
    public String rejectEvent(@PathVariable String id) {
        Event event = eventService.findById(id);
        event.setStatus(Status.REJECTED);
        eventService.update(event);
        return "redirect:/my_events";
    }
}