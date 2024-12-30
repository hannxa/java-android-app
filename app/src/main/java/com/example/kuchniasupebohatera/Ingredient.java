package com.example.kuchniasupebohatera;
public class Ingredient {
    private String ingredient_name;
    private int energyValue;
    private int heartValue;
    private int brainValue;
    private int immunityValue;
    private int image;
    private int amountValue;

    private boolean canButtonBeClicked = false;

    public Ingredient(String ingredient_name, int brainValue, int energyValue, int heartValue, int immunityValue, int amountValue, int imageResourceId){
        this.ingredient_name = ingredient_name;
        this.brainValue = brainValue;
        this.energyValue = energyValue;
        this.heartValue = heartValue;
        this.immunityValue = immunityValue;
        this.amountValue = amountValue;
        this.image = imageResourceId;
    }
    public String getIngredient_name() { return ingredient_name; }
    public int getEnergy() { return energyValue; }
    public int getImmunity() { return immunityValue; }
    public int getHeart() { return heartValue; }
    public int getBrain() { return brainValue; }
    public int getAmountValue() { return amountValue; }
    public int getImageResourceId() { return image; }

    public void increaseAmountValue(){
        this.amountValue++;
    }
    public void decreaseAmountValue(){
        if(this.amountValue > 0){
            this.amountValue--;
        }
    }
    public boolean getCanClicked(){
        return canButtonBeClicked;
    }
    public void setCanButtonBeClicked(boolean buttonClicked){
        canButtonBeClicked = buttonClicked;
    }
}
