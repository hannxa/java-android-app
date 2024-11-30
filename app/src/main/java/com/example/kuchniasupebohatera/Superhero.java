package com.example.kuchniasupebohatera;

import android.view.View;
import android.widget.ImageView;

public class Superhero {
    private boolean empty_indicator;
    private Indicator indicator;
    private ImageView imageId;

    public Superhero(ImageView image, Indicator indicator){
        this.indicator = indicator;
        this.imageId = imageId;
    }


    private void addSuperhero(String superhero_name){

    }

    private void removeSuperhero(String superhero_name){

    }
    public ImageView getImageId() {
        return imageId;
    }

    public Indicator getIndicator() {
        return indicator;
    }
    public void setIndicator(Indicator indicator){
        this.indicator = indicator;
    }
}
