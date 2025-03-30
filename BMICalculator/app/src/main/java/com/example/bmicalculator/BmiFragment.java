package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BmiFragment extends Fragment {

    private EditText heightInput, weightInput;
    private TextView resultNumber, resultText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        heightInput = view.findViewById(R.id.heightInput);
        weightInput = view.findViewById(R.id.weightInput);
        resultNumber = view.findViewById(R.id.resultNumber);
        resultText = view.findViewById(R.id.resultText);
        Button myButton = view.findViewById(R.id.button);

        myButton.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String heightValue = heightInput.getText().toString();
        String weightValue = weightInput.getText().toString();

        if (heightValue.isEmpty() || weightValue.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.please_enter_both_height_and_weight), Toast.LENGTH_SHORT).show();
            return;
        }

        double heightCm = Double.parseDouble(heightValue);
        double weight = Double.parseDouble(weightValue);
        double heightM = heightCm / 100;
        double bmi = weight / (heightM * heightM);

        String formattedBmi = String.format("%.2f", bmi);

        resultNumber.setText(getString(R.string.bmi) + ": " + formattedBmi);
        resultText.setText(getBMICategory(bmi));
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return getString(R.string.underweight);
        if (bmi < 25) return getString(R.string.optimal);
        if (bmi < 30) return getString(R.string.overweight);
        return getString(R.string.overweight);
    }
}