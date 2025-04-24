package com.example.bmicalculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class KcalCalculatorFragmentTest {

    @Test
    public void testCalculateDailyCalories_sedentary() {
        double result = KcalCalculatorFragmentUtils.calculateDailyCalories(180, 75, 25, "Sedentary (little/no exercise)");
        assertEquals(2178.66, result, 1);
    }

    @Test
    public void testCalculateDailyCalories_active() {
        double result = KcalCalculatorFragmentUtils.calculateDailyCalories(170, 65, 30, "Active (6-7 days/week)");
        assertEquals(2768.08, result, 1);
    }

    @Test
    public void testCalculateDailyCalories_invalidActivityLevelDefaultsToSedentary() {
        double result = KcalCalculatorFragmentUtils.calculateDailyCalories(160, 60, 28, "Unknown");
        double expected = (88.362 + (13.397 * 60) + (4.799 * 160) - (5.677 * 28)) * 1.2;
        assertEquals(expected, result, 0.1);
    }
}
