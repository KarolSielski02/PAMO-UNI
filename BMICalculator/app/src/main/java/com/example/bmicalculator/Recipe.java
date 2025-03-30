package com.example.bmicalculator;

public class Recipe {
    private String name;
    private String description;
    private int minCalories;
    private int maxCalories;

    public Recipe(String name, String description, int minCalories, int maxCalories) {
        this.name = name;
        this.description = description;
        this.minCalories = minCalories;
        this.maxCalories = maxCalories;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean matchesCalorieRange(double userCalories) {
        return userCalories >= minCalories && userCalories <= maxCalories;
    }
}