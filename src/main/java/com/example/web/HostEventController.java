package com.example.web;

import com.example.exceptions.NoSuchIDException;
import com.example.model.*;
import com.example.model.Enumerations.Status;
import com.example.repository.LocationRepository;
import com.example.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
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


    public HostEventController(EventService eventService, LocationRepository locationRepository,
                               ClientService clientService, BandService bandService,
                               CateringService cateringService, PhotographerService photographerService,
                               UserService userService) {
        this.eventService = eventService;
        this.locationRepository = locationRepository;
        this.clientService = clientService;
        this.bandService = bandService;
        this.cateringService = cateringService;
        this.photographerService = photographerService;
        this.userService = userService;
    }

    @GetMapping("/host_event")
    public String getHostEventPage(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("bands", bandService.findAll());
        model.addAttribute("caterings", cateringService.findAll());
        model.addAttribute("photographers", photographerService.findAll());
        model.addAttribute("content", "host_event");
        return "main";
    }

    @Transactional
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
                throw new EntityNotFoundException("Client not found with ID: " + user.getId());
            }

            Optional<User> optionalUser = userService.findByUsername("admin");
            Admin admin;
            if (optionalUser.isPresent()) {
                User u = optionalUser.get();
                if (u instanceof Admin) {
                    admin = (Admin) u;
                } else {
                    throw new ClassCastException("The user found is not an admin");
                }
            } else {
                throw new UsernameNotFoundException("Admin user not found");
            }

            admin.setNumberEvents(admin.getNumberEvents() + 1);
            client.setNumberEvents(client.getNumberEvents() + 1);

            this.eventService.save(new Event(String.valueOf(time), date, location2, type, description, client, bands, caterings, photographers, admin, Status.CREATED));

            return "redirect:/home?eventSuccess=true";
        } catch (Exception e) {
            return "redirect:/?error=" + e.getMessage();
        }
    }

    @GetMapping("/my_events")
    public String getEventsForUser(HttpServletRequest req, Model model) {
        User user = userService.findByUsername((String) req.getSession().getAttribute("username")).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Event> events;

        try {
            if (clientService.existsById(user.getId())) {
                Client client = clientService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = client.getEvents();
            } else if (cateringService.existsById(user.getId())) {
                Catering catering = cateringService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = catering.getEventList();
            } else if (bandService.existsById(user.getId())) {
                Band band = bandService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = band.getEventList();
            } else if (photographerService.existsById(user.getId())) {
                Photographer photographer = photographerService.findById(user.getId()).orElseThrow(() -> new NoSuchIDException(user.getId()));
                events = photographer.getEventList();
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
    public String getEventInfo(@PathVariable Integer id, Model model) {
        Event event = eventService.findById(id);

        List<String> bandList = new ArrayList<>();
        for (int i = 0; i < event.getBandList().size(); i++) {
            bandList.add(event.getBandList().get(i).getName());
        }

        List<String> cateringList = new ArrayList<>();
        for (int i = 0; i < event.getCateringList().size(); i++) {
            cateringList.add(event.getCateringList().get(i).getName());
        }

        List<String> photographerList = new ArrayList<>();
        for (int i = 0; i < event.getPhotographerList().size(); i++) {
            photographerList.add(event.getPhotographerList().get(i).getName());
        }

        model.addAttribute("event", event);
        model.addAttribute("bandList", bandList);
        model.addAttribute("cateringList", cateringList);
        model.addAttribute("photographerList", photographerList);
        model.addAttribute("content", "more_details");
        return "main";
    }

    @PostMapping("/approveEvent/{id}")
    public String approveEvent(@PathVariable Integer id, HttpServletResponse response) {
        Event event = eventService.findById(id);
        event.setStatus(Status.APPROVED);
        eventService.update(event);
        return "redirect:/my_events";
    }

    @PostMapping("/rejectEvent/{id}")
    public String rejectEvent(@PathVariable Integer id, HttpServletResponse response) {
        Event event = eventService.findById(id);
        event.setStatus(Status.REJECTED);
        eventService.update(event);
        return "redirect:/my_events";
    }
}