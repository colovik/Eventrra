package com.example.web;

import com.example.exceptions.NoSuchIDException;
import com.example.model.*;
import com.example.repository.EventRepository;
import com.example.repository.LocationRepository;
import com.example.repository.UserRepository;
import com.example.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class HostEventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ClientService clientService;
    private final BandService bandService;
    private final CateringService cateringService;
    private final PhotographerService photographerService;
    private final UserService userService;

    private final WaiterService waiterService;

    public HostEventController(EventRepository eventRepository, UserRepository userRepository,
                               LocationRepository locationRepository, ClientService clientService,
                               BandService bandService, CateringService cateringService,
                               PhotographerService photographerService, UserService userService,
                               WaiterService waiterService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.clientService = clientService;
        this.bandService = bandService;
        this.cateringService = cateringService;
        this.photographerService = photographerService;
        this.userService = userService;
        this.waiterService = waiterService;
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
                                  HttpServletRequest req,
                                  HttpServletResponse resp,
                                  HttpSession session) {
        Event event = null;
        try {
            Location location2 = locationRepository.findByAddress(location);

            User user = (User) userService.findByUsername((String) req.getSession().getAttribute("username")).get();
//            Client client = (Client) clientRepository.findById(user.getId()).get();
//            Admin admin = (Admin) userService.findByUsername("admin").get();

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
                    // Use admin as needed
                } else {
                    throw new ClassCastException("The user found is not an admin");
                }
            } else {
                throw new UsernameNotFoundException("Admin user not found");
            }


            admin.setNumberEvents(admin.getNumberEvents() + 1);
            client.setNumberEvents(client.getNumberEvents() + 1);
            eventRepository.save(new Event(String.valueOf(time), date, location2, type, description, client, bands, caterings, photographers, admin));

            return "redirect:/home?UspeshnoZakazanNastan";
        } catch (Exception e) {
            return "redirect:/?error=" + e.getMessage();
        }
    }

    @GetMapping("/my_events")
    public String getEventsForUser(HttpServletRequest req, Model model) {
        User user = (User) userService.findByUsername((String) req.getSession().getAttribute("username")).get();
        List<Event> events = null;
        try {
            Optional<Client> optionalClient = clientService.findById(user.getId());
            Client client;
            if (optionalClient.isPresent()) {
                client = optionalClient.get();
            } else {
                throw new NoSuchIDException(user.getId());
            }
//            Client client = (Client) clientService.findById(user.getId()).get();
            events = client.getEvent();
        } catch (NoSuchIDException exception) {
            try {
                Optional<Catering> optionalCatering = cateringService.findById(user.getId());
                Catering catering;
                if (optionalCatering.isPresent()) {
                    catering = optionalCatering.get();
                } else {
                    throw new NoSuchIDException(user.getId());
                }
//                Catering catering = (Catering) cateringService.findById(user.getId()).get();
                events = catering.getEventList();
            } catch (NoSuchIDException exception1) {
                try {
                    Optional<Band> optionalBand = bandService.findById(user.getId());
                    Band band;
                    if (optionalBand.isPresent()) {
                        band = optionalBand.get();
                    } else {
                        throw new NoSuchIDException(user.getId());
                    }
//                    Band band = (Band) bandRepository.findById(user.getId()).get();
                    events = band.getEventList();
                } catch (NoSuchIDException e) {
                    try {
                        Optional<Photographer> optionalPhotographer = photographerService.findById(user.getId());
                        Photographer photographer;
                        if (optionalPhotographer.isPresent()) {
                            photographer = optionalPhotographer.get();
                        } else {
                            throw new NoSuchIDException(user.getId());
                        }
//                        Photographer photographer = (Photographer) photographerRepository.findById(user.getId()).get();
                        events = photographer.getEventList();
                    } catch (NoSuchIDException exception2) {
                        try {
                            Optional<Waiter> optionalWaiter = waiterService.findById(user.getId());
                            Waiter waiter;
                            if (optionalWaiter.isPresent()) {
                                waiter = optionalWaiter.get();
                            } else {
                                throw new NoSuchIDException(user.getId());
                            }
//                            Waiter waiter = (Waiter) waiterRepository.findById(user.getId()).get();
                            events = waiter.getEventList();
                        } catch (NoSuchIDException exception3) {
                            events = eventRepository.findAll();
                        }
                    }
                }
            }
        }
        model.addAttribute("events", events);
        model.addAttribute("content", "my_events");
        return "main";
    }
}