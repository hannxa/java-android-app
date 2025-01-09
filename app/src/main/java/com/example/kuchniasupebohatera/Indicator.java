package com.example.kuchniasupebohatera;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;

/**
 * This class represents progress indicators such as energy, heart, immunity, and brain.
 * It is responsible for updating and managing the state of these indicators.
 */
public class Indicator {
    private ProgressBar energyBar, heartBar, immunityBar, brainBar;
    private TextView energyText, heartText, immunityText, brainText;

    /**
     * Constructor for the Indicator class.
     * Initializes the progress bars and text views for all indicators.
     *
     * @param energy_value Progress bar representing energy.
     * @param energy_text Text view representing the energy value.
     * @param heart_value Progress bar representing the heart.
     * @param heart_text Text view representing the heart value.
     * @param immunity_value Progress bar representing immunity.
     * @param immunity_text Text view representing the immunity value.
     * @param brain_value Progress bar representing the brain.
     * @param brain_text Text view representing the brain value.
     */
    public Indicator(ProgressBar energy_value, TextView energy_text, ProgressBar heart_value,TextView
            heart_text, ProgressBar immunity_value, TextView immunity_text,ProgressBar brain_value, TextView brain_text){
        this.energyBar = energy_value;
        this.heartBar = heart_value;
        this.immunityBar = immunity_value;
        this.brainBar = brain_value;
        this.energyText = energy_text;
        this.brainText =brain_text;
        this.heartText = heart_text;
        this.immunityText = immunity_text;
    }

    /**
     * Sets the visibility of all indicators (progress bars and text views).
     *
     * @param visibility The new visibility for all indicators (e.g., View.VISIBLE or View.GONE).
     */
    public void setVisibility(int visibility){
        energyBar.setVisibility(visibility);
        heartBar.setVisibility(visibility);
        immunityBar.setVisibility(visibility);
        brainBar.setVisibility(visibility);
        energyText.setVisibility(visibility);
        heartText.setVisibility(visibility);
        immunityText.setVisibility(visibility);
        brainText.setVisibility(visibility);
    }

    /**
     * Updates the progress indicators based on the selected ingredients.
     * For each chosen ingredient, the respective progress bars (energy, immunity, heart, and brain)
     * are incremented by the corresponding values of the ingredient.
     */
    public void changingIndicators(){
        List<Ingredient> chosenIngredients = IngredientAdapter.getIngredients();

        for(Ingredient ingredient : chosenIngredients){
            energyBar.incrementProgressBy(ingredient.getEnergy());
            immunityBar.incrementProgressBy(ingredient.getImmunity());
            heartBar.incrementProgressBy(ingredient.getHeart());
            brainBar.incrementProgressBy(ingredient.getBrain());
        }
    }

    /**
     * Decreases the progress of all indicators by a fixed amount.
     * This simulates a reduction in energy, immunity, heart health, and brain function.
     */
    public void decreasingIndicators(){
        energyBar.incrementProgressBy(-5);
        immunityBar.incrementProgressBy(-5);
        heartBar.incrementProgressBy(-5);
        brainBar.incrementProgressBy(-5);
    }

    /**
     * Increases the progress of all indicators by a fixed amount based on a recipe.
     * This simulates a boost in energy, immunity, heart health, and brain function from a recipe.
     */
    public void increasingIndicatorsBcRecipe(){
        energyBar.incrementProgressBy(50);
        immunityBar.incrementProgressBy(50);
        heartBar.incrementProgressBy(50);
        brainBar.incrementProgressBy(50);
    }

    /**
     * Resets all indicators to a progress value of 50.
     */
    public void setIndicators(){
        energyBar.setProgress(50);
        immunityBar.setProgress(50);
        heartBar.setProgress(50);
        brainBar.setProgress(50);
    }
    /**
     * Gets the progress bar for energy.
     *
     * @return The energy progress bar.
     */
    public ProgressBar getEnergy() {
        return energyBar;
    }

    /**
     * Gets the progress bar for heart.
     *
     * @return The heart progress bar.
     */
    public ProgressBar getHeart() {
        return heartBar;
    }

    /**
     * Gets the progress bar for brain.
     *
     * @return The brain progress bar.
     */
    public ProgressBar getBrain() {
        return brainBar;
    }

    /**
     * Gets the progress bar for immunity.
     *
     * @return The immunity progress bar.
     */
    public ProgressBar getImmunity() {
        return immunityBar;
    }
}
