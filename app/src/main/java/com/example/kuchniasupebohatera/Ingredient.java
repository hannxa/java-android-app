package com.example.kuchniasupebohatera;

/**
 * Represents an ingredient with various attributes such as energy, heart, brain, and immunity values,
 * along with an image and the current amount in the pantry.
 */
public class Ingredient {
    private String ingredient_name;
    private int energyValue, heartValue, brainValue, immunityValue, image, amountValue;
    private boolean canButtonBeClicked = false;

    /**
     * Constructor for the Ingredient class.
     * Initializes the ingredient with its name, values for energy, heart, brain, and immunity,
     * amount in the pantry, and the associated image resource ID.
     *
     * @param ingredient_name The name of the ingredient.
     * @param brainValue The value representing the brain boost provided by the ingredient.
     * @param energyValue The value representing the energy boost provided by the ingredient.
     * @param heartValue The value representing the heart boost provided by the ingredient.
     * @param immunityValue The value representing the immunity boost provided by the ingredient.
     * @param amountValue The current amount of the ingredient in the pantry.
     * @param imageResourceId The resource ID of the ingredient's image.
     */
    public Ingredient(String ingredient_name, int brainValue, int energyValue, int heartValue, int immunityValue, int amountValue, int imageResourceId){
        this.ingredient_name = ingredient_name;
        this.brainValue = brainValue;
        this.energyValue = energyValue;
        this.heartValue = heartValue;
        this.immunityValue = immunityValue;
        this.amountValue = amountValue;
        this.image = imageResourceId;
    }

    /**
     * Increases the amount of the ingredient in the pantry by 1.
     */
    public void increaseAmountValue(){
        this.amountValue++;
    }

    /**
     * Decreases the amount of the ingredient in the pantry by 1,
     * ensuring the amount does not go below 0.
     */
    public void decreaseAmountValue(){
        if(this.amountValue > 0){
            this.amountValue--;
        }
    }

    /**
     * Sets whether the button associated with the ingredient can be clicked.
     *
     * @param buttonClicked A boolean indicating if the button can be clicked.
     */
    public void setCanButtonBeClicked(boolean buttonClicked) {
        canButtonBeClicked = buttonClicked;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getIngredient_name() {
        return ingredient_name;
    }

    /**
     * Gets the energy value of the ingredient.
     *
     * @return The energy value of the ingredient.
     */
    public int getEnergy() {
        return energyValue;
    }

    /**
     * Gets the immunity value of the ingredient.
     *
     * @return The immunity value of the ingredient.
     */
    public int getImmunity() {
        return immunityValue;
    }

    /**
     * Gets the heart value of the ingredient.
     *
     * @return The heart value of the ingredient.
     */
    public int getHeart() {
        return heartValue;
    }

    /**
     * Gets the brain value of the ingredient.
     *
     * @return The brain value of the ingredient.
     */
    public int getBrain() {
        return brainValue;
    }

    /**
     * Gets the current amount of the ingredient in the pantry.
     *
     * @return The current amount of the ingredient.
     */
    public int getAmountValue() {
        return amountValue;
    }

    /**
     * Gets the resource ID of the ingredient's image.
     *
     * @return The resource ID of the image.
     */
    public int getImageResourceId() {
        return image;
    }

    /**
     * Checks if the button associated with the ingredient can be clicked.
     *
     * @return True if the button can be clicked, false otherwise.
     */
    public boolean getCanClicked() {
        return canButtonBeClicked;
    }
}
