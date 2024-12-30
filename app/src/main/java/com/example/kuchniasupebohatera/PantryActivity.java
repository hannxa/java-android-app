package com.example.kuchniasupebohatera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PantryActivity extends AppCompatActivity {

private IngredientAdapter adapter;
public static List<Ingredient> ingredientsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        settingMenu();
        // Znajdź RecyclerView w widoku
        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new IngredientAdapter(ingredientsList);
        recyclerView.setAdapter(adapter);
    }
    public List<Ingredient> getIngredientsList(){
        if(ingredientsList.isEmpty()){
            initializeIngredients();
        }
        return ingredientsList;
    }
    private void settingMenu(){
        ImageButton bookButton = findViewById(R.id.book_button);
        ImageButton homeButton = findViewById(R.id.home_button);

        bookButton.setOnClickListener(v -> {
            Intent goBook = new Intent(PantryActivity.this, RecipeBookActivity.class);
            startActivity(goBook);
        });
        homeButton.setOnClickListener(v -> {
            Intent goHome = new Intent(PantryActivity.this, MainActivity.class);
            startActivity(goHome);
        });
    }

    private void initializeIngredients(){
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
        ingredientsList.add(new Ingredient("chipsy", -3, -1, -6, -7,0,R.drawable.chipsy));
        ingredientsList.add(new Ingredient("słodzony napój", -6, 1, -8, -9,0,R.drawable.napoj));
        ingredientsList.add(new Ingredient("zupka błyskawiczna", -9, -3, -8, -6,0,R.drawable.zupka));
        ingredientsList.add(new Ingredient("pączek", -7, -10, -6, -8,0,R.drawable.paczek));
    }
}