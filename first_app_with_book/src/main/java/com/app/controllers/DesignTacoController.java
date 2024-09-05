package com.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.data.IngredientRepository;
import com.app.models.Ingredient;
import com.app.models.Taco;
import com.app.models.TacoOrder;
import com.app.models.Type;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

   private final IngredientRepository ingredientRepo;

   public DesignTacoController(IngredientRepository ingredientRepository) {
      this.ingredientRepo = ingredientRepository;
   }

   @ModelAttribute
   public void addIngredientsToModel(Model model) {
      Iterable<Ingredient> ingredients = ingredientRepo.findAll();
      Type[] types = Type.values();
      for (Type type : types) {
         model.addAttribute(type.toString().toLowerCase(),
               filterByType((List<Ingredient>) ingredients, type));
      }
   }

   // @ModelAttribute
   // public void addIngredientsToModel(Model model) {
   // List<Ingredient> ingredients = Arrays.asList(
   // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
   // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
   // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
   // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
   // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
   // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
   // new Ingredient("CHED", "Cheddar", Type.CHEESE),
   // new Ingredient("JACK", "Monterry Jack", Type.CHEESE),
   // new Ingredient("SLSA", "Salsa", Type.SAUCE),
   // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

   // Type[] types = Type.values();
   // for (Type type : types) {
   // model.addAttribute(type.toString().toLowerCase(),
   // filterByType(ingredients, type));
   // }
   // }

   @ModelAttribute(name = "tacoOrder")
   public TacoOrder order() {
      return new TacoOrder();
   }

   @ModelAttribute(name = "taco")
   public Taco taco() {
      return new Taco();
   }

   @GetMapping
   public String showDesignForm() {
      return "design";
   }

   @PostMapping
   public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
      if (errors.hasErrors()) {
         return "design";
      }
      tacoOrder.addTaco(taco);
      log.info("Processing taco: {}", taco);
      return "redirect:/orders/current";
   }

   public String postMethodName(@RequestBody String entity) {

      return entity;
   }

   private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
      return ingredients.stream()
            .filter(ing -> ing.getType().equals(type))
            .collect(Collectors.toList());
   }
}
