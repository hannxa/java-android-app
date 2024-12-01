package com.example.kuchniasupebohatera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<Superhero> superheroList = new ArrayList<>();
    List<Ingredient> ingredientsList;
    private TextView message1, message2, message3, message4;
    private Superhero superhero1, superhero2, superhero3, superhero4;

    private Handler handler = new Handler();
    private Runnable runnable;
    private int interval = 3000;
    private Random randomH, randomI;

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
        //łaczenie komponentow
        settingMenu();
        settingSuperheros();

        superheroList.get(2).setVisibility(View.INVISIBLE);
        superheroList.get(3).setVisibility(View.INVISIBLE);
        System.out.println("rozmiar listy " + superheroList.size());

        //uzyskanie listy skladnikow
        Ingredient ingredientMenager = new Ingredient();
        ingredientsList = ingredientMenager.getIngredientsList();
        for(Ingredient ingredient: ingredientsList){
            System.out.println(ingredientsList);
        }

        //losowanie supebohatera
        randomH = new Random();
        Superhero randomHero = superheroList.get(randomH.nextInt(superheroList.size()));

        System.out.println("wylosowany bohater: " + randomHero);


        //losowanie skladnikow
        randomI = new Random();
        Ingredient randomIngredient = ingredientsList.get(randomI.nextInt(ingredientsList.size()));
        System.out.println("wylosowany skladnik: " + randomIngredient);

        //wyswietlanie wiadomosci
        settingMessage();
    }
    private void settingMessage(){
        runnable = new Runnable(){
            @Override
            public void run() {
                //losowanie supebohatera i skladnika
                randomH = new Random();
                randomI = new Random();

                Ingredient randomIngredient = ingredientsList.get(randomI.nextInt(ingredientsList.size()));
                Superhero randomHero = superheroList.get(randomH.nextInt(superheroList.size()));

                if(randomHero == superhero1){
                    message1.setText(randomIngredient.getIngredient_name());
                }
                if(randomHero == superhero2){
                    message2.setText(randomIngredient.getIngredient_name());
                }
                if(randomHero == superhero3){
                    message3.setText(randomIngredient.getIngredient_name());
                }
                if(randomHero == superhero4){
                    message4.setText(randomIngredient.getIngredient_name());
                }
                handler.postDelayed(this, interval); //wywolanie tej samej funkcji po okreslonym czasie
            }
        };
        handler.post(runnable);
    }
    private void settingMenu(){
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

    private void settingSuperheros(){
        ImageView superhero1Image = findViewById(R.id.imageView4);
        ProgressBar superhero1Energy = findViewById(R.id.hero1Energy);
        TextView energyText1 = findViewById(R.id.textEnergia);
        ProgressBar superhero1Immunity = findViewById(R.id.hero1Immunity);
        TextView brainText1 = findViewById(R.id.textMozg1);
        ProgressBar superhero1Heart = findViewById(R.id.hero1Heart);
        TextView heartText1 = findViewById(R.id.textSerce1);
        ProgressBar superhero1Brain = findViewById(R.id.hero1Brain);
        TextView immunityText1 = findViewById(R.id.textOdpornosc1);
        message1 = findViewById(R.id.message1);

        ImageView superhero2Image = findViewById(R.id.imageView5);
        ProgressBar superhero2Energy = findViewById(R.id.hero2Energy);
        TextView energyText2 = findViewById(R.id.textEnergy2);
        ProgressBar superhero2Immunity = findViewById(R.id.hero2Immunity);
        TextView immunityText2 = findViewById(R.id.textImmunity2);
        ProgressBar superhero2Heart = findViewById(R.id.hero2Heart);
        TextView heartText2 = findViewById(R.id.textHeart2);
        ProgressBar superhero2Brain = findViewById(R.id.hero2Brain);
        TextView brainText2 = findViewById(R.id.textBrain2);
        message2 = findViewById(R.id.message2);

        ImageView superhero3Image = findViewById(R.id.imageView6);
        ProgressBar superhero3Energy = findViewById(R.id.hero3Energy);
        TextView energyText3 = findViewById(R.id.textEnergy3);
        ProgressBar superhero3Immunity = findViewById(R.id.hero3Immunity);
        TextView immunityText3 = findViewById(R.id.textImmunity3);
        ProgressBar superhero3Heart = findViewById(R.id.hero3Heart);
        TextView heartText3 = findViewById(R.id.textHeart3);
        ProgressBar superhero3Brain = findViewById(R.id.hero3Brain);
        TextView brainText3 = findViewById(R.id.textBrain3);
        message3 = findViewById(R.id.message3);

        ImageView superhero4Image = findViewById(R.id.imageView7);
        ProgressBar superhero4Energy = findViewById(R.id.hero4Energy);
        TextView energyText4 = findViewById(R.id.textEnergy4);
        ProgressBar superhero4Immunity = findViewById(R.id.hero4Immunity);
        TextView immunityText4 = findViewById(R.id.textImmunity4);
        ProgressBar superhero4Heart = findViewById(R.id.hero4Heart);
        TextView heartText4 = findViewById(R.id.textHeart4);
        ProgressBar superhero4Brain = findViewById(R.id.hero4Brain);
        TextView brainText4 = findViewById(R.id.textBrain4);
        message4 = findViewById(R.id.message4);

        superhero1 = new Superhero( superhero1Image, message1, new Indicator(superhero1Energy, energyText1, superhero1Heart, heartText1,superhero1Immunity,immunityText1, superhero1Brain, brainText1));
        superhero2 = new Superhero( superhero2Image, message2, new Indicator(superhero2Energy, energyText2, superhero2Heart, heartText2,superhero2Immunity,immunityText2, superhero2Brain, brainText2));
        superhero3 = new Superhero( superhero3Image, message3, new Indicator(superhero3Energy, energyText3, superhero3Heart, heartText3,superhero3Immunity,immunityText3, superhero3Brain, brainText3));
        superhero4 = new Superhero( superhero4Image, message4, new Indicator(superhero4Energy, energyText4, superhero4Heart, heartText4,superhero4Immunity,immunityText4, superhero4Brain, brainText4));

        superheroList.add(superhero1);
        superheroList.add(superhero2);
        superheroList.add(superhero3);
        superheroList.add(superhero4);
    }

}