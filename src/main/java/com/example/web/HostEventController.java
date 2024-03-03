package com.example.web;

import com.example.model.*;
import com.example.repository.*;
import com.example.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class HostEventController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ClientRepository clientRepository;
    private final BandRepository bandRepository;
    private final CateringRepository cateringRepository;
    private final PhotographerRepository photographerRepository;
    private final UserService userService;

    private final WaiterRepository waiterRepository;
    public HostEventController(EventRepository eventRepository, UserRepository userRepository,
                               LocationRepository locationRepository, ClientRepository clientRepository,
                               BandRepository bandRepository, CateringRepository cateringRepository,
                               PhotographerRepository photographerRepository, UserService userService, WaiterRepository waiterRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.clientRepository = clientRepository;
        this.bandRepository = bandRepository;
        this.cateringRepository = cateringRepository;
        this.photographerRepository = photographerRepository;
        this.userService = userService;
        this.waiterRepository = waiterRepository;
    }

    @GetMapping("/host_event")
    public String getHostEventPage(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("bands", bandRepository.findAll());
        model.addAttribute("caterings", cateringRepository.findAll());
        model.addAttribute("photographers", photographerRepository.findAll());
        return "host_event";
    }

    @Transactional
    @PostMapping("/host_event")
    public String handleHostEvent(@RequestParam String time,
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
            Client client = (Client) clientRepository.findById(user.getId()).get();
            Admin admin = (Admin) userService.findByUsername("admin1").get();
            admin.setNumber_events(admin.getNumber_events()+1);
            client.setNumber_events(client.getNumber_events()+1);
            eventRepository.save(new Event(time, date, location2, type, description, client, bands,caterings, photographers, admin));

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
            Client client = (Client) clientRepository.findById(user.getId()).get();
            events = client.getEvent();
        }
        catch (NoSuchElementException exception){
            try{
                Catering catering = (Catering) cateringRepository.findById(user.getId()).get();
                events = catering.getEventList();
            }
            catch (NoSuchElementException exception1){
                try {
                    Band band = (Band) bandRepository.findById(user.getId()).get();
                    events = band.getEventList();
                }
                catch (NoSuchElementException e){
                    try{
                        Photographer photographer = (Photographer) photographerRepository.findById(user.getId()).get();
                        events = photographer.getEventList();
                    }
                    catch (NoSuchElementException exception2){
                        try{
                            Waiter waiter = (Waiter) waiterRepository.findById(user.getId()).get();
                            events = waiter.getEventList();
                        }
                        catch (NoSuchElementException exception3){
                            events = eventRepository.findAll();
                        }
                    }
                }
            }
        }
        model.addAttribute("events",events);
        return "my_events";
    }
}