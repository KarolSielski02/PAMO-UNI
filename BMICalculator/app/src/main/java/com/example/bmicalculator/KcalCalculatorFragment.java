package com.example.bmicalculator;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class KcalCalculatorFragment extends Fragment {

    private EditText heightInput, weightInput, ageInput;
    private Spinner activityInput;
    private TextView resultText;
    private String selectedActivityLevel;
    private Button calculateButton;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kcal_calculator, container, false);

        heightInput = view.findViewById(R.id.kc_heightInput);
        weightInput = view.findViewById(R.id.kc_weightInput);
        ageInput = view.findViewById(R.id.kc_ageInput);
        activityInput = view.findViewById(R.id.kc_activityInput);
        resultText = view.findViewById(R.id.kc_resultText);
        calculateButton = view.findViewById(R.id.kc_calculateButton);

        loadActivityLevels();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalories();
            }
        });

        return view;
    }

    private void calculateCalories() {
        try {
            int height = Integer.parseInt(heightInput.getText().toString());
            int weight = Integer.parseInt(weightInput.getText().toString());
            int age = Integer.parseInt(ageInput.getText().toString());

            // Harris-Benedict formula for men
            double bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);

            double activityMultiplier = getActivityMultiplier(selectedActivityLevel);
            double dailyCalories = bmr * activityMultiplier;

            sharedViewModel.setDailyCalories(dailyCalories);

            resultText.setText(String.format("Daily Caloric Needs: %.2f kcal", dailyCalories));

        } catch (NumberFormatException e) {
            resultText.setText(getString(R.string.invalid_input));
        }
    }

    private double getActivityMultiplier(String activityLevel) {
        switch (activityLevel) {
            case "Sedentary (little/no exercise)": return 1.2;
            case "Light (1-3 days/week)": return 1.375;
            case "Moderate (3-5 days/week)": return 1.55;
            case "Active (6-7 days/week)": return 1.725;
            case "Very Active (athlete)": return 1.9;
            default: return 1.2;
        }
    }


    private void loadActivityLevels() {
        Resources res = getResources();
        String[] activityLevels = res.getStringArray(R.array.activity_levels);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, activityLevels);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activityInput.setAdapter(adapter);

        activityInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedActivityLevel = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no item is selected, if needed
            }
        });
    }
}
