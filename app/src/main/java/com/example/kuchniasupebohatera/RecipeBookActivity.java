package com.example.kuchniasupebohatera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Activity representing the recipe book screen where users can view recipes and their ingredients.
 */
public class RecipeBookActivity extends AppCompatActivity {

    public static HashMap<String,List<Ingredient>> recipes;
    /**
     * Called when the activity is starting. Sets up the user interface and initializes the recipe book.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this contains the most recent data supplied. Otherwise, it is null.
     */
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
        settingMenu(); //Initialize the menu, setting up navigation buttons
    }

    /**
     * Sets up the navigation menu with buttons for accessing other screens.
     */
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

    /**
     * Populates the recipe book with predefined recipes and their ingredients.
     * The recipes are mapped to their respective ingredient lists.
     */
    public static void addingRecipes(){
        recipes = new HashMap<>();
        List<Ingredient> pantryIngredient = PantryActivity.ingredientsList;

        List<Ingredient> ingredientsCocktail = new ArrayList<>();
        List<Ingredient> ingredientsSalad = new ArrayList<>();
        List<Ingredient> ingredientsSmoothie = new ArrayList<>();
        List<Ingredient> ingredientsOatMeal = new ArrayList<>();
        List<Ingredient> ingredientsMilk = new ArrayList<>();

        //ingredients for "koktajl mocy"
        ingredientsCocktail.add(pantryIngredient.get(0));
        ingredientsCocktail.add(pantryIngredient.get(1));
        ingredientsCocktail.add(pantryIngredient.get(2));
        ingredientsCocktail.add(pantryIngredient.get(3));
        ingredientsCocktail.add(pantryIngredient.get(5));
        ingredientsCocktail.add(pantryIngredient.get(6));
        ingredientsCocktail.add(pantryIngredient.get(7));
        ingredientsCocktail.add(pantryIngredient.get(8));

        //ingredients for "sałatka"
        ingredientsSalad.add(pantryIngredient.get(2));
        ingredientsSalad.add(pantryIngredient.get(9));
        ingredientsSalad.add(pantryIngredient.get(10));
        ingredientsSalad.add(pantryIngredient.get(11));
        ingredientsSalad.add(pantryIngredient.get(12));
        ingredientsSalad.add(pantryIngredient.get(13));
        ingredientsSalad.add(pantryIngredient.get(6));

        //ingredients for "owsianka"
        ingredientsOatMeal.add(pantryIngredient.get(3));
        ingredientsOatMeal.add(pantryIngredient.get(14));
        ingredientsOatMeal.add(pantryIngredient.get(15));
        ingredientsOatMeal.add(pantryIngredient.get(5));

        //ingredients for "złote mleko"
        ingredientsMilk.add(pantryIngredient.get(3));
        ingredientsMilk.add(pantryIngredient.get(7));
        ingredientsMilk.add(pantryIngredient.get(13));
        ingredientsMilk.add(pantryIngredient.get(5));
        ingredientsMilk.add(pantryIngredient.get(16));

        //ingredients for "smoothie bananowo-szpinakowe"
        ingredientsSmoothie.add(pantryIngredient.get(0));
        ingredientsSmoothie.add(pantryIngredient.get(1));
        ingredientsSmoothie.add(pantryIngredient.get(17));
        ingredientsSmoothie.add(pantryIngredient.get(18));

        // Map recipes to their ingredient lists
        recipes.put("koktajl mocy", ingredientsCocktail);
        recipes.put("sałatke z awokado i pomidorem", ingredientsSalad);
        recipes.put("owsianke z jagodami i orzechami", ingredientsOatMeal);
        recipes.put("złote mleko z kurkumą", ingredientsMilk);
        recipes.put("smoothie bananowo-szpinakowe", ingredientsSmoothie);
    }
}