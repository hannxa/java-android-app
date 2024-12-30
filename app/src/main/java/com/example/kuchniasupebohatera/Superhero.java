package com.example.kuchniasupebohatera;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Superhero {
    private Indicator indicator;
    private final ImageButton image;
    private Button collectButton;
    private TextView message;

    private boolean visibility;
    private Context context;

    public Superhero(Context context, ImageButton image, TextView message, Button collectButton, Indicator indicator){
        this.indicator = indicator;
        this.image = image;
        this.message = message;
        this.collectButton = collectButton;
        this.context = context;
    }

    public void checkingIfToDelete() {
        if (getIndicator().getBrain().getProgress() == 0 ||
                getIndicator().getEnergy().getProgress() == 0 ||
                getIndicator().getHeart().getProgress() == 0 ||
                getIndicator().getImmunity().getProgress() == 0) {
            setVisibility(false);
        }
    }
    public boolean checkingIfToAdd(){
        if(getIndicator().getBrain().getProgress() == getIndicator().getBrain().getMax() &&
                getIndicator().getEnergy().getProgress() == getIndicator().getEnergy().getMax() &&
                getIndicator().getHeart().getProgress() == getIndicator().getHeart().getMax() &&
                getIndicator().getImmunity().getProgress() == getIndicator().getImmunity().getMax() ){
           return true;
        }
        return false;
    }
    public void feedingHero(List<Superhero> heroes){
        image.setOnClickListener(v ->{

            getIndicator().changingIndicators();

            setFeedbackMessage();

            checkingIfToDelete(); //sprawdzenie ze jakis z progressBarów sie wyzerował

            if(checkingIfToAdd()){ // jezeli wszystkie sa wymaksowane to dodajemy kolejnego
                addingHero(heroes);
            }
            savingChanges(heroes);
            IngredientAdapter.getIngredients().clear(); //czyszczenie listy wybranych skladnikow
        });
    }

    private void setFeedbackMessage(){

        String bestMessage = "Ale siła!";
        for (Ingredient ingredient : IngredientAdapter.getIngredients()) {
            if(ingredient.getEnergy() < 0 || ingredient.getHeart() < 0 || ingredient.getBrain() < 0 || ingredient.getImmunity() < 0 ){

                Map<String, Integer> stats = new HashMap<>();
                stats.put("Pora na drzemke", ingredient.getEnergy());
                stats.put("Chyba zemdleje", ingredient.getHeart());
                stats.put("Jak się walczyło?", ingredient.getBrain());
                stats.put("Apsik!", ingredient.getImmunity());

                // Znajdź wiadomość dla najniższego wskaźnika

                int minValue = 0;

                for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                    if (entry.getValue() < minValue) {
                        minValue = entry.getValue();
                        bestMessage = entry.getKey();
                    }
                }
            }

            setMessage(bestMessage);
        }
    }
    public void savingChanges(List<Superhero> superheroList){
        SharedPreferences sharedPreferences = context.getSharedPreferences("GameProgress", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < superheroList.size(); i++) {
            Superhero hero = superheroList.get(i);
            editor.putBoolean("hero_" + i + "_visible", hero.getVisibility());
            editor.putInt("hero_" + i + "_brainProgress", hero.getIndicator().getBrain().getProgress());
            editor.putInt("hero_" + i + "_energyProgress", hero.getIndicator().getEnergy().getProgress());
            editor.putInt("hero_" + i + "_heartProgress", hero.getIndicator().getHeart().getProgress());
            editor.putInt("hero_" + i + "_immunityProgress", hero.getIndicator().getImmunity().getProgress());
        }

        editor.apply();
    }
    private void addingHero(List<Superhero> heroes){
        for(Superhero hero : heroes){
            if(!hero.getVisibility()){
                hero.setVisibility(true);
                break; //tylko jednego dodajemy
            }
        }
    }
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
    public boolean getVisibility(){
        return visibility;
    }

    public void setMessage(String messageText){
        message.setText(messageText);
    }
    public String getMessage(){
        return message.getText().toString();
    }

    public Button getCollectButton(){
        return collectButton;
    }
    public Indicator getIndicator(){
        return indicator;
    }
}
