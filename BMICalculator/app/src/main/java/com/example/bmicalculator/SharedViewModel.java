package com.example.bmicalculator;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Double> dailyCalories = new MutableLiveData<>();

    public LiveData<Double> getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(double calories) {
        dailyCalories.setValue(calories);
    }
}