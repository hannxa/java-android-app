package com.example.kuchniasupebohatera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class RecipeBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_book2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        settingMenu();


    }

    private void settingMenu(){
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton pantryButton = findViewById(R.id.pantryButton);

        pantryButton.setOnClickListener(v -> {
            Intent goPantry = new Intent(RecipeBookActivity.this, PantryActivity.class);
            startActivity(goPantry);
        });
        homeButton.setOnClickListener(v -> {
            Intent goHome = new Intent(RecipeBookActivity.this, MainActivity.class);
            startActivity(goHome);
        });
    }
}