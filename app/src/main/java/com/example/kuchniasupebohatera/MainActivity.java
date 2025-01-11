package com.example.kuchniasupebohatera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * MainActivity is the primary activity of the application that handles the core game logic,
 * including superhero interactions, ingredient management, and game state handling.
 */
public class MainActivity extends AppCompatActivity{
    List<Superhero> superheroList = new ArrayList<>();
    List<Superhero> positiveSuperheroList;
    List<Ingredient> ingredientsList;
    private Superhero superhero1, superhero2, superhero3, superhero4;
    private final Handler handler = new Handler();
    private final int interval = 3500; //Interval for randomizer in milliseconds
    private Random randomH, randomI;

    /**
     * Initializes the activity, setting up the game UI, loading state, and starting game logic.
     *
     * @param savedInstanceState The saved instance state for restoring activity state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);  // Ustaw widok aktywności
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initialize the menu, superheros, and game state
        settingMenu();
        settingSuperheros();
        loadGameState();

        //Get the list of ingredients from PantryActivity
        PantryActivity ingredientManager = new PantryActivity();
        ingredientsList = ingredientManager.getIngredientsList();

        //Initialize game functionality
        randomizer();
        feedingHero();
        ifGameEnded();
    }

    /**
     * Randomizes superhero and ingredient interactions at regular intervals.
     */
    private void randomizer(){
        Runnable runnable;
        runnable = new Runnable(){

            @Override
            public void run() {
                randomH = new Random();
                randomI = new Random();

                positiveSuperheroList = new ArrayList<>(); //visible superheroes
                for(Superhero hero : superheroList){ //if superheros are visible, then they can be chosen to bring ingredients
                    if(hero.getVisibility()){
                        positiveSuperheroList.add(hero);
                    }
                }
                if(!positiveSuperheroList.isEmpty()){
                    Superhero randomHero = positiveSuperheroList.get(randomH.nextInt(positiveSuperheroList.size())); //losowanie superhero, z tych ktorzy sa widczni

                    if(randomHero.getMessage().isEmpty() || isDefaultMessage(randomHero.getMessage())){ //if message empty or has specific message, then there can be displayed ingredient
                        Ingredient randomIngredient = ingredientsList.get(randomI.nextInt(ingredientsList.size())); //draw ingredient
                        randomHero.setMessage(randomIngredient.getIngredient_name()); //set the ingredient as a message

                        randomHero.getIndicator().decreasingIndicators(); // decrease superhero indicators, when he brings food
                        randomHero.checkingIfToDelete(); // Check if superhero should be removed
                        randomHero.savingChanges(superheroList); // Save changes

                        // Set click listener for the collect button
                        randomHero.getCollectButton().setOnClickListener(v -> {
                            randomHero.setMessage(""); //If ingredient is selected, then message turn empty
                            randomIngredient.increaseAmountValue(); // Add ingredient to the pantry
                            randomIngredient.setCanButtonBeClicked(true); //Ingredient can be chosen from the pantry
                        });
                    }
                }
                handler.postDelayed(this, interval); // Schedule the next randomizer run
            }
        };
        handler.post(runnable); // Start the randomizer
    }

    /**
     * Checks if a message is one of the default messages.
     *
     * @param message The message to check.
     * @return True if the message is a default message, false otherwise.
     */
    private boolean isDefaultMessage(String message) {
        return Objects.equals(message, "Ale siła!") ||
                Objects.equals(message, "Pora na drzemke") ||
                Objects.equals(message, "Chyba zemdleje") ||
                Objects.equals(message, "Jak się walczyło?") ||
                Objects.equals(message, "Apsik!");
    }

    /**
     * Handles feeding superheroes and checks if recipes are created.
     */
    private void feedingHero(){
        RecipeBookActivity.addingRecipes(); // Add recipes to the book
        TextView feedbackText = findViewById(R.id.feedbackText);
        settingMessage(); // Display appropriate feedback message

        if(!IngredientAdapter.getIngredients().isEmpty()){ // Check if ingredients are selected
            // Check if one of the recipes has been made
            for(String recipe : RecipeBookActivity.recipes.keySet()){
                List<Ingredient> recipeIngredients = RecipeBookActivity.recipes.get(recipe);

                if(new HashSet<>(IngredientAdapter.chosenIngredients).equals(new HashSet<>(recipeIngredients))){
                    feedbackText.setText("Gratulacje! Udało Ci się stworzyć " + recipe);
                }
            }
            for(Superhero hero : superheroList){
                hero.feedingHero(superheroList); // Feed superhero
            }
        }
    }

    /**
     * Checks if the game has ended and resets it if all superheroes are invisible.
     */
    private void ifGameEnded(){
        int amountOfInvisible = 0;

        // Count invisible superheroes
        for(Superhero hero : superheroList){
            if(!hero.getVisibility()){
                amountOfInvisible++;
            }
        }
        // Reset game if all superheroes are invisible
        if(amountOfInvisible == 4){
            TextView feedbackText = findViewById(R.id.feedbackText);
            feedbackText.setText("Próbuj ponownie!");

            // Reset progress indicators for all superheroes
            for (int i = 0; i < superheroList.size(); i++) {
                Superhero hero = superheroList.get(i);

                hero.getIndicator().getBrain().setProgress(70);
                hero.getIndicator().getEnergy().setProgress(70);
                hero.getIndicator().getHeart().setProgress(70);
                hero.getIndicator().getImmunity().setProgress(70);
            }
            // Make superheroes visible again
            superhero1.setVisibility(true);
            superhero2.setVisibility(true);
        }
    }

    /**
     * Sets feedback messages based on the current game state.
     */
    private void settingMessage(){
        TextView feedbackText = findViewById(R.id.feedbackText);

        if (!IngredientAdapter.getIngredients().isEmpty()) {
            // Prompt the user to feed superheroes
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    feedbackText.setText("Nakarm superbohatera, naciskając go");
                }
            });
        } else {
            for(String recipe : RecipeBookActivity.recipes.keySet()){
                if (!feedbackText.getText().toString().equals("Gratulacje! Udało Ci się stworzyć" + recipe)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Prompt the user to collect ingredients
                            feedbackText.setText("Zbieraj produkty od superbohaterów z ich podróży!");
                        }
                    });
                }
            }
        }
    }

    /**
     * Sets up the menu buttons for navigation to other activities.
     */
    private void settingMenu(){
        Button pantryButton = findViewById(R.id.pantry_button);
        Button bookButton = findViewById(R.id.book_button);

        pantryButton.setOnClickListener(v -> {
            Intent goPantry = new Intent(MainActivity.this, PantryActivity.class);
            startActivity(goPantry);
        });
        bookButton.setOnClickListener(v -> {
            Intent goBook = new Intent(MainActivity.this, RecipeBookActivity.class);
            startActivity(goBook);
        });
    }

    /**
     * Loads the game state from shared preferences.
     */
    private void loadGameState() {
        SharedPreferences sharedPreferences = getSharedPreferences("GameProgress", MODE_PRIVATE);

        for (int i = 0; i < superheroList.size(); i++) {
            Superhero hero = superheroList.get(i);

            // Load progress bar values
            hero.getIndicator().getBrain().setProgress(sharedPreferences.getInt("hero_" + i + "_brainProgress", 50));
            hero.getIndicator().getEnergy().setProgress(sharedPreferences.getInt("hero_" + i + "_energyProgress", 50));
            hero.getIndicator().getHeart().setProgress(sharedPreferences.getInt("hero_" + i + "_heartProgress", 50));
            hero.getIndicator().getImmunity().setProgress(sharedPreferences.getInt("hero_" + i + "_immunityProgress", 50));

            // Load visibility state
            boolean isVisible = sharedPreferences.getBoolean("hero_" + i + "_visible", true);
            hero.setVisibility(isVisible);
        }
    }

    /**
     * Initializes the superheroes and their associated UI elements.
     */
    private void settingSuperheros(){
        // Initialize superhero components
        TextView message1, message2, message3, message4;
        Button collectButton1, collectButton2, collectButton3, collectButton4;

        ImageButton superhero1Image = findViewById(R.id.imageView4);
        ProgressBar superhero1Energy = findViewById(R.id.hero1Energy);
        TextView energyText1 = findViewById(R.id.textEnergia);
        ProgressBar superhero1Immunity = findViewById(R.id.hero1Immunity);
        TextView brainText1 = findViewById(R.id.textMozg1);
        ProgressBar superhero1Heart = findViewById(R.id.hero1Heart);
        TextView heartText1 = findViewById(R.id.textSerce1);
        ProgressBar superhero1Brain = findViewById(R.id.hero1Brain);
        TextView immunityText1 = findViewById(R.id.textOdpornosc1);
        message1 = findViewById(R.id.message1);
        collectButton1 = findViewById(R.id.collectButton1);

        ImageButton superhero2Image = findViewById(R.id.imageView5);
        ProgressBar superhero2Energy = findViewById(R.id.hero2Energy);
        TextView energyText2 = findViewById(R.id.textEnergy2);
        ProgressBar superhero2Immunity = findViewById(R.id.hero2Immunity);
        TextView immunityText2 = findViewById(R.id.textImmunity2);
        ProgressBar superhero2Heart = findViewById(R.id.hero2Heart);
        TextView heartText2 = findViewById(R.id.textHeart2);
        ProgressBar superhero2Brain = findViewById(R.id.hero2Brain);
        TextView brainText2 = findViewById(R.id.textBrain2);
        message2 = findViewById(R.id.message2);
        collectButton2 = findViewById(R.id.collectButton2);

        ImageButton superhero3Image = findViewById(R.id.imageView6);
        ProgressBar superhero3Energy = findViewById(R.id.hero3Energy);
        TextView energyText3 = findViewById(R.id.textEnergy3);
        ProgressBar superhero3Immunity = findViewById(R.id.hero3Immunity);
        TextView immunityText3 = findViewById(R.id.textImmunity3);
        ProgressBar superhero3Heart = findViewById(R.id.hero3Heart);
        TextView heartText3 = findViewById(R.id.textHeart3);
        ProgressBar superhero3Brain = findViewById(R.id.hero3Brain);
        TextView brainText3 = findViewById(R.id.textBrain3);
        message3 = findViewById(R.id.message3);
        collectButton3 = findViewById(R.id.collectButton3);

        ImageButton superhero4Image = findViewById(R.id.imageView7);
        ProgressBar superhero4Energy = findViewById(R.id.hero4Energy);
        TextView energyText4 = findViewById(R.id.textEnergy4);
        ProgressBar superhero4Immunity = findViewById(R.id.hero4Immunity);
        TextView immunityText4 = findViewById(R.id.textImmunity4);
        ProgressBar superhero4Heart = findViewById(R.id.hero4Heart);
        TextView heartText4 = findViewById(R.id.textHeart4);
        ProgressBar superhero4Brain = findViewById(R.id.hero4Brain);
        TextView brainText4 = findViewById(R.id.textBrain4);
        message4 = findViewById(R.id.message4);
        collectButton4 = findViewById(R.id.collectButton4);

        superhero1 = new Superhero( MainActivity.this, superhero1Image, message1, collectButton1, new Indicator(superhero1Energy, energyText1, superhero1Heart, heartText1,superhero1Immunity,immunityText1, superhero1Brain, brainText1));
        superhero2 = new Superhero(MainActivity.this, superhero2Image, message2, collectButton2, new Indicator(superhero2Energy, energyText2, superhero2Heart, heartText2,superhero2Immunity,immunityText2, superhero2Brain, brainText2));
        superhero3 = new Superhero(MainActivity.this, superhero3Image, message3, collectButton3, new Indicator(superhero3Energy, energyText3, superhero3Heart, heartText3,superhero3Immunity,immunityText3, superhero3Brain, brainText3));
        superhero4 = new Superhero(MainActivity.this, superhero4Image, message4, collectButton4, new Indicator(superhero4Energy, energyText4, superhero4Heart, heartText4,superhero4Immunity,immunityText4, superhero4Brain, brainText4));

        superheroList.add(superhero1);
        superheroList.add(superhero2);
        superheroList.add(superhero3);
        superheroList.add(superhero4);

        superhero1.setVisibility(true);
        superhero2.setVisibility(true);
        superhero3.setVisibility(false);
        superhero4.setVisibility(false);

    }
}