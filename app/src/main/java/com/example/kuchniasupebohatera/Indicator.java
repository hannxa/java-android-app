package com.example.kuchniasupebohatera;

import static android.content.Context.MODE_PRIVATE;


import android.content.SharedPreferences;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


public class Indicator {
    private ProgressBar energyBar, heartBar, immunityBar, brainBar;
    private TextView energyText, heartText, immunityText, brainText;


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

    public void changingIndicators(){
        List<Ingredient> chosenIngredients = IngredientAdapter.getIngredients();

        for(Ingredient ingredient : chosenIngredients){
            energyBar.incrementProgressBy(ingredient.getEnergy());
            immunityBar.incrementProgressBy(ingredient.getImmunity());
            heartBar.incrementProgressBy(ingredient.getHeart());
            brainBar.incrementProgressBy(ingredient.getBrain());
        }
    }
    public void decreasingIndicators(){
        energyBar.incrementProgressBy(-5);
        immunityBar.incrementProgressBy(-5);
        heartBar.incrementProgressBy(-5);
        brainBar.incrementProgressBy(-5);
    }

    public ProgressBar getEnergy() {
        return energyBar;
    }

    public ProgressBar getHeart() {
        return heartBar;
    }

    public ProgressBar getBrain() {
        return brainBar;
    }
    public ProgressBar getImmunity() {
        return immunityBar;
    }
}
