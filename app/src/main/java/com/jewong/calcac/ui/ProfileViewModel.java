package com.jewong.calcac.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.jewong.calcac.R;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;

public class ProfileViewModel extends AndroidViewModel {

    public MutableLiveData<String> mWeightInput = new MutableLiveData<>("");
    public MutableLiveData<String> mDietInput = new MutableLiveData<>("");
    public MutableLiveData<String> mWeightGoalInput = new MutableLiveData<>("");

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

}
