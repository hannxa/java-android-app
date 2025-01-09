package com.example.kuchniasupebohatera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for displaying a list of ingredients in a RecyclerView.
 * It manages the interaction between the data and the views.
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredients;
    private boolean canReverse = false;
    public static List<Ingredient> chosenIngredients = new ArrayList<>();

    /**
     * Constructor for the IngredientAdapter.
     *
     * @param ingredients The list of ingredients to display in the RecyclerView.
     */
    public IngredientAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    /**
     * ViewHolder class for binding ingredient data to the views in each item of the RecyclerView.
     */
    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView energyTextView;
        TextView immunityTextView;
        TextView heartTextView;
        TextView brainTextView;
        TextView amount;
        TextView amountValue;
        ImageView ingredientImageView;
        Button selectButton, reverseButton;

        /**
         * Constructor for the ViewHolder.
         * Links the views to their XML layout IDs.
         *
         * @param itemView The view representing a single item in the RecyclerView.
         */
        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.avocadoText);
            energyTextView = itemView.findViewById(R.id.energyText);
            immunityTextView = itemView.findViewById(R.id.immunityText);
            heartTextView = itemView.findViewById(R.id.heartText);
            brainTextView = itemView.findViewById(R.id.brainText);
            amount = itemView.findViewById(R.id.amountText);
            amountValue = itemView.findViewById(R.id.amountValueText);
            ingredientImageView = itemView.findViewById(R.id.avokadoImage);
            selectButton = itemView.findViewById(R.id.addButton);
            reverseButton = itemView.findViewById(R.id.reverseButton);
        }
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for a single RecyclerView item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        // Get the ingredient at the specified position
        Ingredient ingredient = ingredients.get(position);

        // Set data to the views in the ViewHolder
        holder.ingredientName.setText(ingredient.getIngredient_name());
        holder.energyTextView.setText("Energia: " + ingredient.getEnergy());
        holder.immunityTextView.setText("Odporność: " + ingredient.getImmunity());
        holder.heartTextView.setText("Serce: " + ingredient.getHeart());
        holder.brainTextView.setText("Mózg: " + ingredient.getBrain());
        holder.amountValue.setText(String.valueOf(ingredient.getAmountValue()));
        holder.amount.setText("Ilosc: ");
        holder.ingredientImageView.setImageResource(ingredient.getImageResourceId());

        // Handle the select button click
        holder.selectButton.setOnClickListener(v -> {
            if(ingredient.getAmountValue() == 0){
                ingredient.setCanButtonBeClicked(false); // Disable button if no amount is left
            }
            if(ingredient.getCanClicked()){ // Only if select button can be clicked
                setButtonReversibility(true); // Enable reversibility
                ingredient.decreaseAmountValue(); // Decrease the amount
                chosenIngredients.add(ingredient); // Add ingredient to the selected list
                notifyItemChanged(holder.getAdapterPosition()); // Update UI immediately
            }
        });
        // Handle the reverse button click
        holder.reverseButton.setOnClickListener(v -> {
            if(getIsButtonReversible()) {
                setButtonReversibility(false); // Disable reversibility
                ingredient.setCanButtonBeClicked(true); // Allow selection again
                ingredient.increaseAmountValue(); // Increase the amount
                chosenIngredients.remove(ingredient); // Remove ingredient from the selected list
                notifyItemChanged(holder.getAdapterPosition()); // Update UI immediately
            }
        });
    }

    /**
     * Checks if the reverse button is currently enabled.
     *
     * @return True if the reverse button is enabled, false otherwise.
     */
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    /**
     * Checks if the reverse button is currently enabled.
     *
     * @return True if the reverse button is enabled, false otherwise.
     */
    public boolean getIsButtonReversible() {return canReverse;}

    /**
     * Gets the list of currently selected ingredients.
     *
     * @return The list of selected ingredients.
     */
    public static List<Ingredient> getIngredients() {return chosenIngredients;}

    /**
     * Sets the reversibility state of the button.
     *
     * @param canReverse A boolean indicating whether the reverse button can be clicked.
     */
    public void setButtonReversibility(boolean canReverse) {
        this.canReverse = canReverse;
    }
}
