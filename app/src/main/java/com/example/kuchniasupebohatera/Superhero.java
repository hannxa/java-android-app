package com.example.kuchniasupebohatera;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Superhero {
    private Indicator indicator;
    private final ImageView imageView;
    private Button collectButton;
    private TextView message;
    private boolean visibility;

    private Ingredient ingredient;

    public Superhero(ImageView image, TextView message, Button collectButton, Indicator indicator){
        this.indicator = indicator;
        this.imageView = image;
        this.message = message;
        this.collectButton = collectButton;
    }

    public void setVisibility(boolean visible){
        if(visible){
            imageView.setVisibility(View.VISIBLE);
            indicator.setVisibility(View.VISIBLE);
            collectButton.setVisibility(View.VISIBLE);
            visibility = true;
        } else {
            imageView.setVisibility(View.INVISIBLE);
            indicator.setVisibility(View.INVISIBLE);
            collectButton.setVisibility(View.INVISIBLE);
            visibility = false;
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
    public void randomizeMessage(List<Ingredient> ingredients, Random random){
        ingredient = ingredients.get(random.nextInt(ingredients.size()));
        setMessage(ingredient.getIngredient_name());
    }
    public Ingredient getIngredient(){
        return ingredient;
    }
}
