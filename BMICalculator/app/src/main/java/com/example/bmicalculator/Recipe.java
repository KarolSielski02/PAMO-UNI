package com.example.bmicalculator;

public class Recipe {
    private final String name;
    private final String description;
    private final int minCalories;
    private final int maxCalories;

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