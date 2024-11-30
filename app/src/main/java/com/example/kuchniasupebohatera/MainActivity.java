package com.example.kuchniasupebohatera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Superhero> superheroList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);  // Ustaw widok aktywności
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        menu();

        ImageView superhero1Image = findViewById(R.id.imageView4);
        ProgressBar superhero1Energy = findViewById(R.id.hero1Energy);
        TextView energyText1 = findViewById(R.id.textEnergia);
        ProgressBar superhero1Immunity = findViewById(R.id.hero1Immunity);
        TextView brainText1 = findViewById(R.id.textMozg1);
        ProgressBar superhero1Heart = findViewById(R.id.hero1Heart);
        TextView heartText1 = findViewById(R.id.textSerce1);
        ProgressBar superhero1Brain = findViewById(R.id.hero1Brain);
        TextView immunityText1 = findViewById(R.id.textOdpornosc1);

        ImageView superhero2Image = findViewById(R.id.imageView5);
        ProgressBar superhero2Energy = findViewById(R.id.hero2Energy);
        TextView energyText2 = findViewById(R.id.textEnergy2);
        ProgressBar superhero2Immunity = findViewById(R.id.hero2Immunity);
        TextView immunityText2 = findViewById(R.id.textImmunity2);
        ProgressBar superhero2Heart = findViewById(R.id.hero2Heart);
        TextView heartText2 = findViewById(R.id.textHeart2);
        ProgressBar superhero2Brain = findViewById(R.id.hero2Brain);
        TextView brainText2 = findViewById(R.id.textBrain2);

        superheroList.add(new Superhero( superhero1Image, new Indicator(superhero1Energy, energyText1, superhero1Heart, heartText1,superhero1Immunity,immunityText1, superhero1Brain, brainText1)));
        superheroList.add(new Superhero( superhero2Image, new Indicator(superhero2Energy, energyText2, superhero2Heart, heartText2,superhero2Immunity,immunityText2, superhero2Brain, brainText2)));


    }
    private void menu(){
        Button pantryButton = findViewById(R.id.pantry_button);
        Button bookButton = findViewById(R.id.book_button);

        pantryButton.setOnClickListener(v -> {
            Intent goPantry = new Intent(MainActivity.this, PantryActivity.class);
            startActivity(goPantry);
        });
        bookButton.setOnClickListener(v -> {
            Intent goBook = new Intent(MainActivity.this, RecipeBookActivity.class);
            startActivity(goBook);
        });
    }
}