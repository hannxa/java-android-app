package com.example.kuchniasupebohatera;

import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String ingredient_name;
    private int energyValue;
    private int heartValue;
    private int brainValue;
    private int immunityValue;
    private Button addButtom;
    private int image;
    private String amount;
    private int amountValue;
    public static List<Ingredient> ingredientsList = new ArrayList<>();

    public Ingredient(String ingredient_name, int brainValue, int energyValue, int heartValue, int immunityValue, int amountValue, int imageResourceId){
        this.ingredient_name = ingredient_name;
        this.brainValue = brainValue;
        this.energyValue = energyValue;
        this.heartValue = heartValue;
        this.immunityValue = immunityValue;
        this.amountValue = amountValue;
        this.image = imageResourceId;
    }
    public Ingredient(){

    }
    private void initializeIngredients(){ //zostanie to jeszcze zmienione
        ingredientsList.add(new Ingredient("banan", 3, 9, 8, 3,0, R.drawable.banan));
        ingredientsList.add(new Ingredient("szpinak", 8, 4, 9, 5,0,R.drawable.szpinak));
        ingredientsList.add(new Ingredient("awokado", 8, 7, 10, 5,0,R.drawable.awokado));
        ingredientsList.add(new Ingredient("mleko", 3, 5, 4, 4,0,R.drawable.mleko));
        ingredientsList.add(new Ingredient("jogurt naturalny", 5, 5, 3, 6,0,R.drawable.jogurt));
        ingredientsList.add(new Ingredient("miód", 4, 8, 3, 7,0,R.drawable.miod));
        ingredientsList.add(new Ingredient("cytryna", 3, 3, 2, 8,0,R.drawable.cytryna));
        ingredientsList.add(new Ingredient("kurkuma", 5, 2, 5, 9,0,R.drawable.kurkuma));
        ingredientsList.add(new Ingredient("nasiona chia", 9, 5, 10, 4,0,R.drawable.nasiona));
        ingredientsList.add(new Ingredient("pomidory", 3, 2, 9, 5,0,R.drawable.pomidor));
        ingredientsList.add(new Ingredient("cebula", 4, 1, 7, 7,0,R.drawable.cebula));
        ingredientsList.add(new Ingredient("oliwa z oliwek", 7, 2, 10, 4,0,R.drawable.oliwa));
        ingredientsList.add(new Ingredient("sól morska", 1, 1, 2, 0,0,R.drawable.sol));
        ingredientsList.add(new Ingredient("pieprz", 2, 1, 1, 3,0,R.drawable.pieprz));
        ingredientsList.add(new Ingredient("jagody", 10, 5, 6, 9,0,R.drawable.jagody));
        ingredientsList.add(new Ingredient("orzechy włoskie", 10, 5, 9, 4,0,R.drawable.orzechy));
        ingredientsList.add(new Ingredient("cynamon", 4, 2, 2, 6,0,R.drawable.cynamon));
        ingredientsList.add(new Ingredient("woda kokosowa", 2, 7, 4, 3,0,R.drawable.kokos));
        ingredientsList.add(new Ingredient("masło orzechowe", 7, 8, 5, 3,0,R.drawable.maslo));
        ingredientsList.add(new Ingredient("biały cukier", -5, 2, -8, -7,0,R.drawable.cukier));
        ingredientsList.add(new Ingredient("chipsy", -3, -1, -7, -5,0,R.drawable.chipsy));
        ingredientsList.add(new Ingredient("słodzony napój", -6, 1, -9, -8,0,R.drawable.napoj));
        ingredientsList.add(new Ingredient("zupka błyskawiczna", -8, -3, -10, -6,0,R.drawable.zupka));
        ingredientsList.add(new Ingredient("pączek", -7, -10, -6, -8,0,R.drawable.paczek));
    }
    public List<Ingredient> getIngredientsList(){
        initializeIngredients();
        return ingredientsList;
    }

    public String getIngredient_name() { return ingredient_name; }
    public int getEnergy() { return energyValue; }
    public int getImmunity() { return immunityValue; }
    public int getHeart() { return heartValue; }
    public int getBrain() { return brainValue; }
    public String getAmount() { return amount; }
    public int getAmountValue() { return amountValue; }
    public int getImageResourceId() { return image; }
    public Button getAddButtom() {
        return addButtom;
    }

}
