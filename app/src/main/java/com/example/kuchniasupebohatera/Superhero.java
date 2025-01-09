package com.example.kuchniasupebohatera;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Represents a superhero character in the game. Manages their state, visibility, and interactions with the game.
 */
public class Superhero {
    private Indicator indicator;
    private final ImageButton image;
    private Button collectButton;
    private TextView message;
    private boolean visibility;
    private final Context context;

    /**
     * Constructor to initialize a superhero with its associated UI elements and indicator.
     *
     * @param context       Application context
     * @param image         ImageButton representing the superhero
     * @param message       TextView for displaying messages
     * @param collectButton Button for collecting rewards
     * @param indicator     Indicator object representing the superhero's progress
     */
    public Superhero(Context context, ImageButton image, TextView message, Button collectButton, Indicator indicator){
        this.indicator = indicator;
        this.image = image;
        this.message = message;
        this.collectButton = collectButton;
        this.context = context;
    }

    /**
     * Checks if any of the superhero's indicators have reached zero. If so, the superhero becomes invisible.
     */
    public void checkingIfToDelete() {
        if (getIndicator().getBrain().getProgress() == 0 ||
                getIndicator().getEnergy().getProgress() == 0 ||
                getIndicator().getHeart().getProgress() == 0 ||
                getIndicator().getImmunity().getProgress() == 0) {
            setVisibility(false);
        }
    }

    /**
     * Checks if all the superhero's indicators have reached their maximum values.
     * If true, a new superhero can be added to the game.
     *
     * @return True if all indicators are at their maximum values, false otherwise
     */    public boolean checkingIfToAdd(){
        return getIndicator().getBrain().getProgress() == getIndicator().getBrain().getMax() &&
                getIndicator().getEnergy().getProgress() == getIndicator().getEnergy().getMax() &&
                getIndicator().getHeart().getProgress() == getIndicator().getHeart().getMax() &&
                getIndicator().getImmunity().getProgress() == getIndicator().getImmunity().getMax();
    }

    /**
     * Handles feeding the superhero with selected ingredients and updates their state.
     *
     * @param heroes List of all superheroes in the game
     */
    public void feedingHero(List<Superhero> heroes){
        RecipeBookActivity.addingRecipes(); // Add recipes to the book

        // Set up a click listener for the superhero's image.
        image.setOnClickListener(v -> {
            getIndicator().changingIndicators();  // Increase the superhero's progress indicators based on the selected ingredients.
            setFeedbackMessage();  // Update the feedback message displayed to the user.
            checkingIfToDelete(); // Check if any of the superhero's progress bars have reached zero and handle visibility.

            if(checkingIfToAdd()){ // If all progress bars are at their maximum values, add a new superhero to the game.
                addingHero(heroes);
            }
            for (String recipe : RecipeBookActivity.recipes.keySet()) {
                List<Ingredient> recipeIngredients = RecipeBookActivity.recipes.get(recipe);

                if (new HashSet<>(IngredientAdapter.chosenIngredients).equals(new HashSet<>(recipeIngredients))) {
                    getIndicator().increasingIndicatorsBcRecipe(); // Boost indicators one more time by +50 if a recipe is created
                    break;
                }
            }
            savingChanges(heroes);  // Save the updated game state to persistent storage.
            IngredientAdapter.getIngredients().clear(); // Clear the list of selected ingredients after the hero is fed.
        });

    }

    /**
     * Updates the feedback message based on the ingredients selected by the player.
     */
    private void setFeedbackMessage(){
        String bestMessage = "Ale siła!";
        for (Ingredient ingredient : IngredientAdapter.getIngredients()) {
            //if harmful ingredient had been chosen
            if(ingredient.getEnergy() < 0 || ingredient.getHeart() < 0 || ingredient.getBrain() < 0 || ingredient.getImmunity() < 0 ){

                // Create a map to associate specific messages with each stat value.
                Map<String, Integer> stats = new HashMap<>();
                stats.put("Pora na drzemke", ingredient.getEnergy());
                stats.put("Chyba zemdleje", ingredient.getHeart());
                stats.put("Jak się walczyło?", ingredient.getBrain());
                stats.put("Apsik!", ingredient.getImmunity());

                int minValue = 0;
                // Iterate through the map to find the stat with the lowest value.
                for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                    if (entry.getValue() < minValue) {
                        minValue = entry.getValue(); // Update the minimum value.
                        bestMessage = entry.getKey(); // Assign the corresponding message.
                    }
                }
            }
            setMessage(bestMessage);
        }
    }

    /**
     * Saves the state of all superheroes to shared preferences.
     *
     * @param superheroList List of all superheroes in the game
     */
    public void savingChanges(List<Superhero> superheroList){
        // Retrieve shared preferences for saving game progress.
        SharedPreferences sharedPreferences = context.getSharedPreferences("GameProgress", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Loop through the list of superheroes to save their state.
        for (int i = 0; i < superheroList.size(); i++) {
            Superhero hero = superheroList.get(i);
            editor.putBoolean("hero_" + i + "_visible", hero.getVisibility()); // Save the visibility status of the superhero.
            // Save the progress values for each of the superhero's indicators.
            editor.putInt("hero_" + i + "_brainProgress", hero.getIndicator().getBrain().getProgress());
            editor.putInt("hero_" + i + "_energyProgress", hero.getIndicator().getEnergy().getProgress());
            editor.putInt("hero_" + i + "_heartProgress", hero.getIndicator().getHeart().getProgress());
            editor.putInt("hero_" + i + "_immunityProgress", hero.getIndicator().getImmunity().getProgress());
        }
        // Apply the changes to save the updated game state.
        editor.apply();
    }

    /**
     * Adds a new superhero to the game by reactivating an invisible superhero.
     *
     * @param heroes List of all superheroes in the game
     */
    private void addingHero(List<Superhero> heroes){
        for(Superhero hero : heroes){
            if(!hero.getVisibility()){
                hero.getIndicator().setIndicators(); //setting them to be on 50%
                hero.setVisibility(true);
                break; //only one superhero is added
            }
        }
    }

    /**
     * Sets the visibility of the superhero in the game.
     *
     * @param visible True to make the superhero visible, false to hide them
     */
    public void setVisibility(boolean visible){
        if(visible){
            this.image.setVisibility(View.VISIBLE);
            this.indicator.setVisibility(View.VISIBLE);
            this.collectButton.setVisibility(View.VISIBLE);
            this.visibility = true;
            this.message.setVisibility(View.VISIBLE);
        } else {
            this.image.setVisibility(View.INVISIBLE);
            this.indicator.setVisibility(View.INVISIBLE);
            this.collectButton.setVisibility(View.INVISIBLE);
            this.visibility = false;
            this.message.setVisibility(View.INVISIBLE);
        }
    }
    /**
     * Gets the visibility status of the superhero.
     *
     * @return true if the superhero is visible, false otherwise.
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * Sets a feedback message for the superhero.
     *
     * @param messageText the feedback message to display.
     */
    public void setMessage(String messageText) {
        message.setText(messageText);
    }

    /**
     * Retrieves the current feedback message of the superhero.
     *
     * @return the feedback message as a string.
     */
    public String getMessage() {
        return message.getText().toString();
    }

    /**
     * Gets the collect button associated with the superhero.
     *
     * @return the collect button.
     */
    public Button getCollectButton() {
        return collectButton;
    }

    /**
     * Retrieves the indicator object associated with the superhero.
     *
     * @return the superhero's indicator object.
     */
    public Indicator getIndicator() {
        return indicator;
    }
}
