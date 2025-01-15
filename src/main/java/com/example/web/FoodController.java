package com.example.web;

import com.example.exceptions.FoodNotFoundException;
import com.example.model.Allergens;
import com.example.model.Food;
import com.example.service.AllergenService;
import com.example.service.CateringService;
import com.example.service.FoodService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class FoodController {

    private final FoodService foodService;
    private final CateringService cateringService;

    private final AllergenService allergenService;

    public FoodController(FoodService foodService, CateringService cateringService, AllergenService allergenService) {
        this.foodService = foodService;
        this.cateringService = cateringService;
        this.allergenService = allergenService;
    }

    @GetMapping("/food")
    public String getFood(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer cateringId = cateringService.getCateringIdByName(username);

        model.addAttribute("content", "food");
        model.addAttribute("food", foodService.findAllFoodByCateringId(cateringId));
        model.addAttribute("allergens", allergenService.findAll());
        return "main";
    }

    @PostMapping("/food/delete/{id}")
    public String deleteFood(@PathVariable Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer cateringId = cateringService.getCateringIdByName(username);

        cateringService.deleteProductFromCateringOffer(cateringId, id);
        foodService.deleteFoodById(id);
        return "redirect:/food";
    }

    @GetMapping("/food/edit/{id}")
    public String showEditFood(@PathVariable("id") Integer id, Model model) {
        Food food = foodService.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found"));
        List<Allergens> allAllergens = allergenService.findAll();
        List<Allergens> foodAllergens = allergenService.findAllByFoodId(id);
        Set<Integer> foodAllergenIds = foodAllergens.stream()
                .map(Allergens::getId)
                .collect(Collectors.toSet());

        model.addAttribute("food", food);
        model.addAttribute("allAllergens", allAllergens);
        model.addAttribute("foodAllergenIds", foodAllergenIds);  // Ensure this is added
        model.addAttribute("content", "edit_food");
        return "main";
    }

    @PostMapping("/food/edit/{id}")
    public String editFood(@PathVariable("id") Integer id,
                           @ModelAttribute Food food,
                           @RequestParam("selectedAllergens") List<Integer> selectedAllergens,
                           RedirectAttributes redirectAttributes) {
        try {
            food.setId(id);
            List<Allergens> allergens = allergenService.findAllById(selectedAllergens);
            food.setAllergens(allergens);

            foodService.updateFood(food);
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

    @Transactional
    @PostMapping("/food/add")
    public String addFood(@RequestParam String name,
                          @RequestParam Boolean vegetarian,
                          @RequestParam Integer calories,
                          @RequestParam Boolean vegan,
                          @RequestParam List<Integer> allergens) {

        Food food = new Food();
        food.setName(name);
        food.setVegetarian(vegetarian);
        food.setCalories(calories);
        food.setVegan(vegan);

        List<Allergens> allergenList = allergenService.findAllById(allergens);
        food.setAllergens(allergenList);

        foodService.saveFood(food);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer cateringId = cateringService.getCateringIdByName(username);

        cateringService.addProductToCateringOffer(cateringId, food.getId());

        return "redirect:/food";
    }


    @GetMapping("/catering/{id}/food-menu")
    public String getFoodMenu(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("food", this.foodService.findAllFoodByCateringId(id));
        model.addAttribute("content", "food");
        return "main";
    }
}
