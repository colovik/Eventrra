package com.example.web;

import com.example.exceptions.CateringNotFoundException;
import com.example.exceptions.DrinkNotFoundException;
import com.example.model.Catering;
import com.example.model.Drink;
import com.example.service.CateringService;
import com.example.service.DrinkService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DrinksController {

    private final DrinkService drinkService;
    private final CateringService cateringService;
    private final ProductService productService;

    private final UserService userService;


    public DrinksController(DrinkService drinkService, CateringService cateringService,
                            ProductService productService, UserService userService) {
        this.drinkService = drinkService;
        this.cateringService = cateringService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/drinks")
    public String getDrinks(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String cateringId = cateringService.getCateringIdByName(username);

        model.addAttribute("content", "drinks");
        model.addAttribute("drinks", drinkService.findAllDrinksByCateringId(cateringId));
        return "main";
    }

    @PostMapping("/drinks/delete/{id}")
    public String deleteDrink(@PathVariable String id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String cateringId = cateringService.getCateringIdByName(username);

        this.cateringService.deleteProductFromCateringOffer(cateringId, id);
        this.drinkService.deleteDrinkById(String.valueOf(id));
        return "redirect:/drinks";
    }


    @GetMapping("/drinks/edit/{id}")
    public String showEditDrink(@PathVariable("id") String id, Model model) {
        Drink drink = drinkService.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException("Drink not found"));

        model.addAttribute("drink", drink);
        model.addAttribute("content", "edit_drink");

        return "main";
    }

    @PostMapping("/drinks/edit/{id}")
    public String editDrink(@PathVariable("id") String id,
                            @ModelAttribute Drink drink,
                            RedirectAttributes redirectAttributes) {
        try {
            drink.setId(id);

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String cateringId = cateringService.getCateringIdByName(username);
            Catering catering = cateringService.findById(cateringId)
                    .orElseThrow(() -> new CateringNotFoundException("Catering with id %s not found", cateringId));
            drink.setCatering(catering);

            drinkService.updateDrink(drink);
            productService.saveProduct(drink);

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


        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String cateringId = cateringService.getCateringIdByName(username);
        Catering catering = cateringService.findById(cateringId)
                .orElseThrow(() -> new CateringNotFoundException("Catering with id %s not found", cateringId));
        drink.setCatering(catering);

        catering.getProductIds().add(drink.getId());
        cateringService.save(catering); //persisting changes about adding product to the catering
        userService.save(catering);

        drinkService.saveDrink(drink);
        cateringService.addProductToCateringOffer(cateringId, drink.getId());
        this.productService.saveProduct(drink);

        return "redirect:/drinks";
    }

    @GetMapping("/catering/{id}/drinks-menu")
    public String getDrinksMenu(@PathVariable("id") String id, Model model) {
        model.addAttribute("drinks", this.drinkService.findAllDrinksByCateringId(id));
        model.addAttribute("content", "drinks");

        return "main";
    }

}


