package com.example.web;

import com.example.model.Enumerations.Status;
import com.example.model.Event;
import com.example.service.BandService;
import com.example.service.CateringService;
import com.example.service.EventService;
import com.example.service.PhotographerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final EventService eventService;

    private final BandService bandService;
    private final PhotographerService photographerService;
    private final CateringService cateringService;

    public HomeController(EventService eventService, BandService bandService,
                          PhotographerService photographerService, CateringService cateringService) {
        this.eventService = eventService;
        this.bandService = bandService;
        this.photographerService = photographerService;
        this.cateringService = cateringService;
    }


    @GetMapping
    public String getHomePage() {
        return "home";
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
        return "more_details";
    }

    @PostMapping("/approveEvent/{id}")
    public String  approveEvent(@PathVariable Integer id, HttpServletResponse response){
        Event event = eventService.findById(id);
        event.setStatus(Status.APPROVED);
        eventService.update(event);
        return "redirect:/my_events";
    }

    @PostMapping("/rejectEvent/{id}")
    public String  rejectEvent(@PathVariable Integer id, HttpServletResponse response){
        Event event = eventService.findById(id);
        event.setStatus(Status.REJECTED);
        eventService.update(event);
        return "redirect:/my_events";
    }
}
