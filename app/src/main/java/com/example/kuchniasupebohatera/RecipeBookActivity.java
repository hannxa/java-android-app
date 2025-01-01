package com.example.kuchniasupebohatera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RecipeBookActivity extends AppCompatActivity {

    public static HashMap<String,List<Ingredient>> recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_book2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        settingMenu();

    }
    public static void addingRecipes(){
        recipes = new HashMap<>();
        List<Ingredient> pantryIngredient = PantryActivity.ingredientsList;

        List<Ingredient> ingredientsCocktail = new ArrayList<>();
        List<Ingredient> ingredientsSalad = new ArrayList<>();
        List<Ingredient> ingredientsSmoothie = new ArrayList<>();
        List<Ingredient> ingredientsOatMeal = new ArrayList<>();
        List<Ingredient> ingredientsMilk = new ArrayList<>();


        //banan szpinak awokado mleko miod cytryna kukruma nasiona chia
        ingredientsCocktail.add(pantryIngredient.get(0));
        ingredientsCocktail.add(pantryIngredient.get(1));
        ingredientsCocktail.add(pantryIngredient.get(2));
        ingredientsCocktail.add(pantryIngredient.get(3));
        ingredientsCocktail.add(pantryIngredient.get(5));
        ingredientsCocktail.add(pantryIngredient.get(6));
        ingredientsCocktail.add(pantryIngredient.get(7));
        ingredientsCocktail.add(pantryIngredient.get(8));

        //awokado, pomidory,cebula,oliwa z oliwek, sol, piepirz,cytryna,szpinak
        ingredientsSalad.add(pantryIngredient.get(2));
        ingredientsSalad.add(pantryIngredient.get(9));
        ingredientsSalad.add(pantryIngredient.get(10));
        ingredientsSalad.add(pantryIngredient.get(11));
        ingredientsSalad.add(pantryIngredient.get(12));
        ingredientsSalad.add(pantryIngredient.get(13));
        ingredientsSalad.add(pantryIngredient.get(6));
        ingredientsSalad.add(pantryIngredient.get(1));

        //mleko, jagidtm orzechy, miod
        ingredientsOatMeal.add(pantryIngredient.get(3));
        ingredientsOatMeal.add(pantryIngredient.get(14));
        ingredientsOatMeal.add(pantryIngredient.get(15));
        ingredientsOatMeal.add(pantryIngredient.get(5));

        //mleko,kukruma,pieprz,miod,cynamon
        ingredientsMilk.add(pantryIngredient.get(3));
        ingredientsMilk.add(pantryIngredient.get(7));
        ingredientsMilk.add(pantryIngredient.get(13));
        ingredientsMilk.add(pantryIngredient.get(5));
        ingredientsMilk.add(pantryIngredient.get(16));

        //banan,szpinak,kokos,maslo
        ingredientsSmoothie.add(pantryIngredient.get(0));
        ingredientsSmoothie.add(pantryIngredient.get(1));
        ingredientsSmoothie.add(pantryIngredient.get(17));
        ingredientsSmoothie.add(pantryIngredient.get(18));

        recipes.put("koktjl mocy", ingredientsCocktail);
        recipes.put("sałatke z awokado i pomidorem", ingredientsSalad);
        recipes.put("owsianke z jagodami i orzechami", ingredientsOatMeal);
        recipes.put("złote mleko z kurkumą", ingredientsMilk);
        recipes.put("smoothie bananowo-szpinakowe", ingredientsSmoothie);
    }

    private void settingMenu(){
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton pantryButton = findViewById(R.id.pantryButton);

        pantryButton.setOnClickListener(v -> {
            Intent goPantry = new Intent(RecipeBookActivity.this, PantryActivity.class);
            startActivity(goPantry);
        });
        homeButton.setOnClickListener(v -> {
            Intent goHome = new Intent(RecipeBookActivity.this, MainActivity.class);
            startActivity(goHome);
        });
    }
}