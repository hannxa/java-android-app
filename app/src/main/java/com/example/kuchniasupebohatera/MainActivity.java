package com.example.kuchniasupebohatera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity{
    List<Superhero> superheroList = new ArrayList<>();
    List<Superhero> positiveSuperheroList;
    List<Ingredient> ingredientsList;
    private Superhero superhero1, superhero2, superhero3, superhero4;
    private Handler handler = new Handler();
    private final int interval = 3000;
    private Random randomH, randomI;
    private IngredientAdapter adapterList = new IngredientAdapter();
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

        settingMenu();
        settingSuperheros();

        superhero1.setVisibility(true);
        superhero2.setVisibility(true);
        superhero3.setVisibility(false);
        superhero4.setVisibility(false);

        //uzyskanie listy skladnikow
        PantryActivity ingredientManager = new PantryActivity();
        ingredientsList = ingredientManager.getIngredientsList();

        //wyswietlanie wiadomosci i zbieranie produktow
        settingMessage();
    }
    private void settingMessage(){
        Runnable runnable;

        runnable = new Runnable(){

            @Override
            public void run() {
                //losowanie supebohatera i skladnika
                randomH = new Random();
                randomI = new Random();

                positiveSuperheroList = new ArrayList<>();
                for(Superhero hero : superheroList){ //jezeli sa widoczni to ich dodaj do listy
                    if(hero.getVisibility()){
                        positiveSuperheroList.add(hero);
                    }
                }
                if(!positiveSuperheroList.isEmpty()){
                    Superhero randomHero = positiveSuperheroList.get(randomH.nextInt(positiveSuperheroList.size())); //losowanie superhero, z tych ktorzy sa widczni

                    if(randomHero.getMessage().isEmpty()){ //jezeli nie jest pusty message to nie mozna nic tam dac
                        Ingredient randomIngredient = ingredientsList.get(randomI.nextInt(ingredientsList.size())); //losowanie produktu
                        randomHero.setMessage(randomIngredient.getIngredient_name()); //generowanie wiadomosci o zdobytym produkcie

                        randomHero.getCollectButton().setOnClickListener(v -> {
                            randomHero.setMessage(""); //jak sie zbierze to usuwa sie wiadomosc
                            randomIngredient.increaseAmountValue(); //dodanie do spizarni
                            randomIngredient.setCanButtonBeClicked(true);
                        });
                    }
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
        TextView message1, message2, message3, message4;
        Button collectButton1, collectButton2, collectButton3, collectButton4;


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
        collectButton1 = findViewById(R.id.collectButton1);

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
        collectButton2 = findViewById(R.id.collectButton2);


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
        collectButton3 = findViewById(R.id.collectButton3);


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
        collectButton4 = findViewById(R.id.collectButton4);


        superhero1 = new Superhero( superhero1Image, message1, collectButton1, new Indicator(superhero1Energy, energyText1, superhero1Heart, heartText1,superhero1Immunity,immunityText1, superhero1Brain, brainText1));
        superhero2 = new Superhero( superhero2Image, message2, collectButton2, new Indicator(superhero2Energy, energyText2, superhero2Heart, heartText2,superhero2Immunity,immunityText2, superhero2Brain, brainText2));
        superhero3 = new Superhero( superhero3Image, message3, collectButton3, new Indicator(superhero3Energy, energyText3, superhero3Heart, heartText3,superhero3Immunity,immunityText3, superhero3Brain, brainText3));
        superhero4 = new Superhero( superhero4Image, message4, collectButton4, new Indicator(superhero4Energy, energyText4, superhero4Heart, heartText4,superhero4Immunity,immunityText4, superhero4Brain, brainText4));

        superheroList.add(superhero1);
        superheroList.add(superhero2);
        superheroList.add(superhero3);
        superheroList.add(superhero4);
    }

}