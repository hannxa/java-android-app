package com.example.kuchniasupebohatera;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Superhero {
    private Indicator indicator;
    private ImageView imageView;

    private TextView message;


    public Superhero(ImageView image, TextView message, Indicator indicator){
        this.indicator = indicator;
        this.imageView = image;
        this.message = message;
    }

    public void setVisibility(int visibility){
        imageView.setVisibility(visibility);
        indicator.setVisibility(visibility);
    }
}
