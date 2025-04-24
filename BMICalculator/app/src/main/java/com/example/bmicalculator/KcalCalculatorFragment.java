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
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kcal_calculator, container, false);

        heightInput = view.findViewById(R.id.kc_heightInput);
        weightInput = view.findViewById(R.id.kc_weightInput);
        ageInput = view.findViewById(R.id.kc_ageInput);
        activityInput = view.findViewById(R.id.kc_activityInput);
        resultText = view.findViewById(R.id.kc_resultText);
        Button calculateButton = view.findViewById(R.id.kc_calculateButton);

        loadActivityLevels();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCalculateButton();
            }
        });

        return view;
    }

    private void handleCalculateButton() {
        try {
            int height = Integer.parseInt(heightInput.getText().toString());
            int weight = Integer.parseInt(weightInput.getText().toString());
            int age = Integer.parseInt(ageInput.getText().toString());

            // Harris-Benedict formula for men
            double dailyCalories = KcalCalculatorFragmentUtils.calculateDailyCalories(height, weight, age, selectedActivityLevel);

            sharedViewModel.setDailyCalories(dailyCalories);

            resultText.setText(String.format("Daily Caloric Needs: %.2f kcal", dailyCalories));

        } catch (NumberFormatException e) {
            resultText.setText(getString(R.string.invalid_input));
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
