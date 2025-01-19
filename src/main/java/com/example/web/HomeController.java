package com.example.web;

import com.example.exceptions.LocationNotFoundException;
import com.example.model.Location;
import com.example.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HomeController {

    private final LocationService locationService;

    public HomeController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping(value = {"/", "/home"})
    public String getHomePage(@RequestParam(value = "eventSuccess", required = false) Boolean eventSuccess, Model model) {
        if (eventSuccess != null && eventSuccess) {
            model.addAttribute("eventSuccess", true);
        }

        model.addAttribute("content", "home");

        return "main";
    }


    @GetMapping("/locations")
    public String getLocations(Model model) {
        model.addAttribute("locations", this.locationService.findAll());
        model.addAttribute("content", "locations");
        model.addAttribute("flag", true);

        return "main";
    }

    @GetMapping("/locations/edit/{id}")
    public String showEditLocations(@PathVariable("id") String id,
                                    Model model) {
        Optional<Location> location = this.locationService.findById(id);
        if (location.isPresent()) {
            model.addAttribute("location", location.get());
        } else {
            throw new LocationNotFoundException("Location Not Found");
        }
        model.addAttribute("content", "edit_location");

        return "main";
    }

    @PostMapping("/locations/edit/{id}")
    public String editLocation(@PathVariable String id,
                               @ModelAttribute Location location,
                               RedirectAttributes redirectAttributes) {
        try {
            location.setId(id);
            locationService.save(location);
            redirectAttributes.addFlashAttribute("message", "Location updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error updating location");
        }

        return "redirect:/locations";
    }

    @PostMapping("/locations/delete/{id}")
    public String deleteLocation(@PathVariable("id") String id) {
        this.locationService.deleteLocationById(id);

        return "redirect:/locations";
    }

    @GetMapping("/locations/add")
    public String showAddLocations(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("content", "add_location");

        return "main";
    }

    @PostMapping("/locations/add")
    public String addLocation(@RequestParam String name,
                              @RequestParam String address,
                              @RequestParam String phoneNumber,
                              @RequestParam Integer price) {

        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        location.setPhoneNumber(phoneNumber);
        location.setPrice(price);
        locationService.save(location);

        return "redirect:/locations";
    }

}
