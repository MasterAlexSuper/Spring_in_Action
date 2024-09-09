package com.app.data;

import org.springframework.data.repository.CrudRepository;

import com.app.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
