package com.app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.app.IngredientRef;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private Date createdAt = new Date();

   @NotNull
   @Size(min = 5, message = "Name must be at least 5 characters long")
   private String name;

   @ManyToMany
   @Size(min = 1, message = "You must choose at least 1 ingredient")
   private List<Ingredient> ingredients = new ArrayList<>();

   public void addIngredient(Ingredient ingredient) {
      this.ingredients.add(ingredient);
   }

}
