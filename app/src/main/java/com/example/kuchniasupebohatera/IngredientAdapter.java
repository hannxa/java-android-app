package com.example.kuchniasupebohatera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredients;

    // Konstruktor adaptera
    public IngredientAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // ViewHolder dla RecyclerView
    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView energyTextView;
        TextView immunityTextView;
        TextView heartTextView;
        TextView brainTextView;
        TextView amount;
        TextView amountValue;
        ImageView ingredientImageView;
        Button selectButton;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            // Powiązanie widoków z pliku XML
            ingredientName = itemView.findViewById(R.id.avocadoText);
            energyTextView = itemView.findViewById(R.id.energyText);
            immunityTextView = itemView.findViewById(R.id.immunityText);
            heartTextView = itemView.findViewById(R.id.heartText);
            brainTextView = itemView.findViewById(R.id.brainText);
            amount = itemView.findViewById(R.id.amountText);
            amountValue = itemView.findViewById(R.id.amountValueText);
            ingredientImageView = itemView.findViewById(R.id.avokadoImage);
            selectButton = itemView.findViewById(R.id.addButton);
        }
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Napompowanie widoku z pliku XML
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        // Pobranie składnika z listy
        Ingredient ingredient = ingredients.get(position);

        // Ustawienie danych w widokach
        holder.ingredientName.setText(ingredient.getIngredient_name());
        holder.energyTextView.setText("Energia: " + ingredient.getEnergy());
        holder.immunityTextView.setText("Odporność: " + ingredient.getImmunity());
        holder.heartTextView.setText("Serce: " + ingredient.getHeart());
        holder.brainTextView.setText("Mózg: " + ingredient.getBrain());
        holder.amountValue.setText(String.valueOf(ingredient.getAmountValue()));
        holder.amount.setText("Ilosc: ");
        holder.ingredientImageView.setImageResource(ingredient.getImageResourceId());

        // Obsługa kliknięcia przycisku
        holder.selectButton.setOnClickListener(v -> {
            // Przykładowa obsługa kliknięcia
        });
    }

    @Override
    public int getItemCount() {
        // Zwrócenie liczby elementów w liście
        return ingredients.size();
    }
}
