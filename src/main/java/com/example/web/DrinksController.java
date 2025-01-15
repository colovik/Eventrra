package com.example.web;

import com.example.exceptions.DrinkNotFoundException;
import com.example.model.Drink;
import com.example.service.CateringService;
import com.example.service.DrinkService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DrinksController {

    private final DrinkService drinkService;
    private final CateringService cateringService;


    public DrinksController(DrinkService drinkService, CateringService cateringService) {
        this.drinkService = drinkService;
        this.cateringService = cateringService;
    }

    @GetMapping("/drinks")
    public String getDrinks(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer cateringId = cateringService.getCateringIdByName(username);

        model.addAttribute("content", "drinks");
        model.addAttribute("drinks", drinkService.findAllDrinksByCateringId(cateringId));
        return "main";
    }

    @PostMapping("/drinks/delete/{id}")
    public String deleteDrink(@PathVariable Integer id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer cateringId = cateringService.getCateringIdByName(username);

        this.cateringService.deleteProductFromCateringOffer(cateringId, id);
        this.drinkService.deleteDrinkById(id);
        return "redirect:/drinks";
    }


    @GetMapping("/drinks/edit/{id}")
    public String showEditDrink(@PathVariable("id") Integer id, Model model) {

        Drink drink = drinkService.findById(id).orElseThrow(() -> new DrinkNotFoundException("Drink not found"));

        model.addAttribute("drink", drink);
        model.addAttribute("content", "edit_drink");

        return "main";
    }

    @PostMapping("/drinks/edit/{id}")
    public String editDrink(@PathVariable("id") Integer id,
                            @ModelAttribute Drink drink,
                            RedirectAttributes redirectAttributes) {
        try {
            drink.setId(id);
            drinkService.updateDrink(drink);
            redirectAttributes.addFlashAttribute("message", "Drink updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error updating drink");
        }
        return "redirect:/drinks";
    }

    @GetMapping("/drinks/add")
    public String showAddDrink(Model model) {
        model.addAttribute("content", "add_drink");
        return "main";
    }

    @PostMapping("/drinks/add")
    public String addDrink(@RequestParam String name,
                           @RequestParam Boolean isAlcoholic,
                           @RequestParam Integer percentsAlcohol) {

        Drink drink = new Drink();
        drink.setName(name);
        drink.setIsAlcoholic(isAlcoholic);
        drink.setPercentsAlcohol(percentsAlcohol);

        drinkService.saveDrink(drink);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer cateringId = cateringService.getCateringIdByName(username);
        cateringService.addProductToCateringOffer(cateringId, drink.getId());

        return "redirect:/drinks";
    }

    @GetMapping("/catering/{id}/drinks-menu")
    public String getDrinksMenu(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("drinks", this.drinkService.findAllDrinksByCateringId(id));
        model.addAttribute("content", "drinks");
        return "main";
    }

}


