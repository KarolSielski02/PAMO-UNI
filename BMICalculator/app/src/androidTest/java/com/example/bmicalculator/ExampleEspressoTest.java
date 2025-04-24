// File: BmiFragmentTest.java
package com.example.bmicalculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.startsWith;

import android.os.Bundle;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleEspressoTest {

    @Test
    public void testCalculateBMI_showsResult() {
        // Launch the fragment directly using FragmentScenario
        FragmentScenario<BmiFragment> scenario = FragmentScenario.launchInContainer(BmiFragment.class, new Bundle(), R.style.SplashTheme, (FragmentFactory) null);

        // Fill in height and weight
        onView(withId(R.id.heightInput)).perform(typeText("180"), closeSoftKeyboard());
        onView(withId(R.id.weightInput)).perform(typeText("75"), closeSoftKeyboard());

        // Click the button
        onView(withId(R.id.button)).perform(click());

        // Check if resultNumber displays a BMI starting with "BMI: "
        onView(withId(R.id.resultNumber))
                .check(matches(withText(startsWith("BMI: "))));

        // Optional: check if resultText shows "Optimal" (if strings.xml has correct value)
        onView(withId(R.id.resultText))
                .check(matches(anyOf(
                        withText(R.string.optimal),       // This works only with proper test context
                        withText("Optimal")               // Fallback: hardcoded check
                )));
    }
}