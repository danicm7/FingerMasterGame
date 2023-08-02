package com.example.fingermastergame.ui.playerHistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayerHistoryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PlayerHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}