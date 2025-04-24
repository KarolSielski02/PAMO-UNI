package com.example.bmicalculator;

public class KcalCalculatorFragmentUtils {

    public static double calculateDailyCalories(int height, int weight, int age, String activityLevel) {
        double bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        double activityMultiplier = getActivityMultiplier(activityLevel);
        return bmr * activityMultiplier;
    }

    private static double getActivityMultiplier(String activityLevel) {
        switch (activityLevel) {
            case "Sedentary (little/no exercise)": return 1.2;
            case "Light (1-3 days/week)": return 1.375;
            case "Moderate (3-5 days/week)": return 1.55;
            case "Active (6-7 days/week)": return 1.725;
            case "Very Active (athlete)": return 1.9;
            default: return 1.2;
        }
    }
}
