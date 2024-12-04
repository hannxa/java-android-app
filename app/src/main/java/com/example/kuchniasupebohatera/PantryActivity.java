package com.example.kuchniasupebohatera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class PantryActivity extends AppCompatActivity {
public static List<String> scoredIngredient = new ArrayList<>();

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

        TextView textView = findViewById(R.id.scoredIngredientText);
        settingMenu();
        StringBuilder ingredientsText = new StringBuilder();

        for(String ingredient : scoredIngredient){
            ingredientsText.append(ingredient).append("\n");
        }
        textView.setText(ingredientsText.toString());
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