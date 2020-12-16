package com.salatt.droidcafe;

public class Recipe {
    private final int recipeImage;
    private final String recipeTitle;
    private final String recipeDescription;

    Recipe(int recipeImage, String recipeTitle, String recipeDescription){
        this.recipeImage = recipeImage;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
    }

    public int getRecipeImage(){
        return recipeImage;
    }
    public String getRecipeTitle(){
        return recipeTitle;
    }
    public String getRecipeDescription(){
        return recipeDescription;
    }
}
