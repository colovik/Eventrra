package com.example.eventrramongodb.web;

import com.example.eventrramongodb.exceptions.CateringNotFoundException;
import com.example.eventrramongodb.exceptions.FoodNotFoundException;
import com.example.eventrramongodb.model.Allergens;
import com.example.eventrramongodb.model.Catering;
import com.example.eventrramongodb.model.Food;
import com.example.eventrramongodb.model.Product;
import com.example.eventrramongodb.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class FoodController {

    private final FoodService foodService;
    private final CateringService cateringService;
    private final AllergenService allergenService;

    private final ProductService productService;
    private final UserService userService;

    public FoodController(FoodService foodService, CateringService cateringService,
                          AllergenService allergenService, ProductService productService,
                          UserService userService) {
        this.foodService = foodService;
        this.cateringService = cateringService;
        this.allergenService = allergenService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/food")
    public String getFood(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String cateringId = cateringService.getCateringIdByName(username);

        model.addAttribute("content", "food");
        model.addAttribute("food", foodService.findAllFoodByCateringId(cateringId));
        model.addAttribute("allergens", allergenService.findAll());
        return "main";
    }

    @PostMapping("/food/delete/{id}")
    public String deleteFood(@PathVariable String id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String cateringId = cateringService.getCateringIdByName(username);

        cateringService.deleteProductFromCateringOffer(cateringId, id);
        foodService.deleteFoodById(String.valueOf(id));
        return "redirect:/food";
    }

    @GetMapping("/food/edit/{id}")
    public String showEditFood(@PathVariable("id") String id, Model model) {
        Food food = foodService.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found"));
        List<Allergens> allAllergens = allergenService.findAll();
        List<String> foodAllergenIds = food.getAllergenIds();

        model.addAttribute("food", food);
        model.addAttribute("allAllergens", allAllergens);
        model.addAttribute("foodAllergenIds", foodAllergenIds);
        model.addAttribute("content", "edit_food");
        return "main";
    }

    @PostMapping("/food/edit/{id}")
    public String editFood(@PathVariable("id") String id,
                           @ModelAttribute Food food,
                           @RequestParam("selectedAllergens") List<String> selectedAllergens,
                           RedirectAttributes redirectAttributes) {
        try {
            food.setId(id);
            food.setAllergenIds(selectedAllergens);

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String cateringId = cateringService.getCateringIdByName(username);
            Catering catering = this.cateringService.findById(cateringId)
                    .orElseThrow(() -> new CateringNotFoundException("Catering with id %s not found", cateringId));
            food.setCatering(catering);

            foodService.updateFood(food);
            productService.updateProduct(food);

            redirectAttributes.addFlashAttribute("message", "Food updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error updating food");
        }
        return "redirect:/food";
    }

    @GetMapping("/food/add")
    public String showAddFood(Model model) {
        model.addAttribute("allergens", allergenService.findAll());
        model.addAttribute("content", "add_food");
        return "main";
    }

    @PostMapping("/food/add")
    public String addFood(@RequestParam String name,
                          @RequestParam Boolean vegetarian,
                          @RequestParam Integer calories,
                          @RequestParam Boolean vegan,
                          @RequestParam List<String> allergens) {

        Food food = new Food();
        food.setName(name);
        food.setVegetarian(vegetarian);
        food.setCalories(calories);
        food.setVegan(vegan);

        List<Allergens> allergenList = allergenService.findAllById(allergens);
        List<String> allergenIds = allergenList.stream()
                .map(Allergens::getId)
                .collect(Collectors.toList());
        food.setAllergenIds(allergenIds);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String cateringId = cateringService.getCateringIdByName(username);
        Catering catering = this.cateringService.findById(cateringId)
                .orElseThrow(() -> new CateringNotFoundException("Catering with id %s not found", cateringId));
        food.setCatering(catering);

        catering.getProductIds().add(food.getId());
        cateringService.save(catering); //persisting changes about adding product to the catering
        userService.save(catering);

        foodService.saveFood(food);
        cateringService.addProductToCateringOffer(cateringId, food.getId());
        this.productService.saveProduct(food);

        return "redirect:/food";
    }


    @GetMapping("/catering/{id}/food-menu")
    public String getFoodMenu(@PathVariable("id") String id, Model model) {
        model.addAttribute("food", this.foodService.findAllFoodByCateringId(id));
        model.addAttribute("allergens", this.allergenService.findAll());
        model.addAttribute("content", "food");

        return "main";
    }
}
