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

public class PantryActivity extends AppCompatActivity {

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

        menu();

    }

    private void menu(){
        ImageButton bookButton = findViewById(R.id.books_button);
        ImageButton homeButton = findViewById(R.id.home_button);

        bookButton.setOnClickListener(v -> {
            Intent goRecipes = new Intent(PantryActivity.this, RecipeBookActivity.class);
            startActivity(goRecipes);
        });
        bookButton.setOnClickListener(v -> {
            Intent goBook = new Intent(PantryActivity.this, RecipeBookActivity.class);
            startActivity(goBook);
        });
    }
}