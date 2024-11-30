package com.example.kuchniasupebohatera;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;

public class Indicator {
    private ProgressBar energyBar, heartBar, immunityBar, brainBar;
    private TextView energyText, heartText, immunityText, brainText;

    public Indicator(ProgressBar energy_value, TextView energy_text, ProgressBar heart_value,TextView heart_text, ProgressBar immunity_value, TextView immunity_text,ProgressBar brain_value, TextView brain_text){
        this.energyBar = energy_value;
        this.heartBar = heart_value;
        this.immunityBar = immunity_value;
        this.brainBar = brain_value;
        this.energyText = energy_text;
        this.brainText =brain_text;
        this.heartText = heart_text;
        this.immunityText = immunity_text;
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
