package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class DietRecommendationFragment extends Fragment {
    private double userCalories = 2000.0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diet_recomendation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView recommendationTextView = view.findViewById(R.id.recommendationText);
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getDailyCalories().observe(getViewLifecycleOwner(), calories -> {
            userCalories = calories;
            displayRecommendations(userCalories, recommendationTextView);
        });

    }

    private void displayRecommendations(double userCalories, TextView textView) {
        List<Recipe> recommendedRecipes = getRecommendedRecipes(userCalories);
        StringBuilder recommendations = new StringBuilder();

        if (recommendedRecipes.isEmpty()) {
            recommendations.append("No matching recipes found.");
        } else {
            for (Recipe recipe : recommendedRecipes) {
                recommendations.append("🍽 ").append(recipe.getName()).append("\n")
                        .append(recipe.getDescription()).append("\n\n");
            }
        }

        textView.setText(recommendations.toString());
    }

    private List<Recipe> getRecommendedRecipes(double userCalories) {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("Grilled Chicken Salad",
                "Healthy grilled chicken with fresh vegetables.", Integer.MIN_VALUE, 2000));
        recipes.add(new Recipe("Oatmeal with Fruits",
                "Oatmeal served with fresh berries and nuts.", Integer.MIN_VALUE, 2000));

        recipes.add(new Recipe("Salmon with Quinoa",
                "Baked salmon served with quinoa and steamed veggies.", 2000, 2500));
        recipes.add(new Recipe("Pasta with Chicken",
                "Whole grain pasta with grilled chicken and light sauce.", 2000, 2500));

        recipes.add(new Recipe("Steak with Roasted Potatoes",
                "Grilled steak with herb-roasted potatoes.", 2500, Integer.MAX_VALUE));
        recipes.add(new Recipe("Protein Smoothie",
                "High-calorie smoothie with bananas, peanut butter, and oats.", 2500, Integer.MAX_VALUE));

        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.matchesCalorieRange(userCalories)) {
                filteredRecipes.add(recipe);
            }
        }

        return filteredRecipes;
    }
}