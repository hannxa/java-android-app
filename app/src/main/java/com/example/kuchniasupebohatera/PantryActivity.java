package com.example.kuchniasupebohatera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class PantryActivity extends AppCompatActivity {
public List<Ingredient> scoredIngredient = new ArrayList<>();

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
        settingPantry();
    }


    private void settingPantry(){
        //mozliwe jest wybranie skladnika do do przepisu, jak dostanie dodany do scoredIngredients
        //zwieksza sie ich liczba

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
}