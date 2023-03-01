package com.example.aut2_03.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("WELCOME \uD83D\uDEEB✨\n'Travel Planner' is waiting for you\nEnjoy :)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}