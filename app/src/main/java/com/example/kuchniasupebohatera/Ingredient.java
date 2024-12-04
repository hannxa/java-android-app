package com.example.kuchniasupebohatera;

import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ingredient {
    private String ingredient_name;
    private int energyValue;
    private int heartValue;
    private int brainValue;
    private int immunityValue;
    private Button addButtom;
    private ImageView image;
    private List<Ingredient> ingredientsList = new ArrayList<>();

    public Ingredient(String ingredient_name, int brainValue, int energyValue, int heartValue, int immunityValue){
        this.ingredient_name = ingredient_name;
        this.brainValue = brainValue;
        this.energyValue = energyValue;
        this.heartValue = heartValue;
        this.immunityValue = immunityValue;
    }
    public Ingredient(){

    }
    //
    private void initializeIngredients(){ //zostanie to jeszcze zmienione
        ingredientsList.add(new Ingredient("banan", 3, 9, 8, 3));
        ingredientsList.add(new Ingredient("szpinak", 8, 4, 9, 5));
        ingredientsList.add(new Ingredient("awokado", 8, 7, 10, 5));
        ingredientsList.add(new Ingredient("nleko", 3, 5, 4, 4));
        ingredientsList.add(new Ingredient("naturalny jogurt", 5, 5, 3, 6));
        ingredientsList.add(new Ingredient("miód", 4, 8, 3, 7));
        ingredientsList.add(new Ingredient("cytryna", 3, 3, 2, 8));
        ingredientsList.add(new Ingredient("kurkuma", 5, 2, 5, 9));
        ingredientsList.add(new Ingredient("nasiona chia", 9, 5, 10, 4));
        ingredientsList.add(new Ingredient("pomidory", 3, 2, 9, 5));
        ingredientsList.add(new Ingredient("cebula", 4, 1, 7, 7));
        ingredientsList.add(new Ingredient("oliwa z oliwek", 7, 2, 10, 4));
        ingredientsList.add(new Ingredient("sól morska", 1, 1, 2, 0));
        ingredientsList.add(new Ingredient("pieprz", 2, 1, 1, 3));
        ingredientsList.add(new Ingredient("jagody", 10, 5, 6, 9));
        ingredientsList.add(new Ingredient("orzechy włoskie", 10, 5, 9, 4));
        ingredientsList.add(new Ingredient("cynamon", 4, 2, 2, 6));
        ingredientsList.add(new Ingredient("woda kokosowa", 2, 7, 4, 3));
        ingredientsList.add(new Ingredient("masło orzechowe", 7, 8, 5, 3));
        ingredientsList.add(new Ingredient("biały cukier", -5, 2, -8, -7));
        ingredientsList.add(new Ingredient("chipsy", -3, -1, -7, -5));
        ingredientsList.add(new Ingredient("słodzony napój", -6, 1, -9, -8));
        ingredientsList.add(new Ingredient("zupka błyskawiczna", -8, -3, -10, -6));
        ingredientsList.add(new Ingredient("pączek", -7, -10, -6, -8));
    }
    public List<Ingredient> getIngredientsList(){
        initializeIngredients();
        return ingredientsList;
    }


    public String getIngredient_name(){
        return ingredient_name;
    }

}
