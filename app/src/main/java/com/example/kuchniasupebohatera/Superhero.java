package com.example.kuchniasupebohatera;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class Superhero {
    private Indicator indicator;
    private final ImageButton image;
    private Button collectButton;
    private TextView message;
    private boolean visibility;

    private Ingredient ingredient;

    public Superhero(ImageButton image, TextView message, Button collectButton, Indicator indicator){
        this.indicator = indicator;
        this.image = image;
        this.message = message;
        this.collectButton = collectButton;
    }

    public void setVisibility(boolean visible){
        if(visible){
            image.setVisibility(View.VISIBLE);
            indicator.setVisibility(View.VISIBLE);
            collectButton.setVisibility(View.VISIBLE);
            visibility = true;
        } else {
            image.setVisibility(View.INVISIBLE);
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
    public ImageButton getImage(){
        return image;
    }
    public Indicator getIndicator(){
        return indicator;
    }
}
