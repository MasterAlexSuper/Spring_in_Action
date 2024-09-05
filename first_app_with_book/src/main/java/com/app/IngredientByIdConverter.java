package com.app;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.app.data.IngredientRepository;
import com.app.models.Ingredient;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

   private IngredientRepository ingredientRepo;

   public IngredientByIdConverter(IngredientRepository ingredientRepository) {
      this.ingredientRepo = ingredientRepository;
   }

   @Override
   public Ingredient convert(String id) {
      return ingredientRepo.findById(id).orElse(null);
   }

}
