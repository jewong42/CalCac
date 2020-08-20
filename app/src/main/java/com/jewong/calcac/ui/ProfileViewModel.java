package com.jewong.calcac.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.jewong.calcac.common.MathUtils;
import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.model.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository mProfileRepository;
    public LiveData<User> mUser;
    public LiveData<String> mCalories;
    public LiveData<String> mFat;
    public LiveData<String> mCarbohydrates;
    public LiveData<String> mProtein;
    public MutableLiveData<String> mWeightInput = new MutableLiveData<>("");
    public MutableLiveData<String> mDietInput = new MutableLiveData<>("");
    public MutableLiveData<String> mWeightGoalInput = new MutableLiveData<>("");

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mProfileRepository = new ProfileRepository(application);
        initLiveData();
    }

    private void initLiveData() {
        mUser = mProfileRepository.getUser();
        mCalories = Transformations.map(mUser, user -> String.valueOf(MathUtils.getCalories(user)));
        mFat = Transformations.map(mUser, user -> String.valueOf(MathUtils.getFats(user)));
        mCarbohydrates = Transformations.map(mUser, user -> String.valueOf(MathUtils.getCarbohydrates(user)));
        mProtein = Transformations.map(mUser, user -> String.valueOf(MathUtils.getProtein(user)));
        if (mUser.getValue() != null) {
            mWeightInput.setValue(String.valueOf(mUser.getValue().getWeight()));
//        mWeightInput.setValue(String.valueOf(mUser.getValue().getDiet()));
//        mWeightInput.setValue(String.valueOf(mUser.getValue().getWeightGoal()));
        }
    }

    public void deleteProfile() {
        mProfileRepository.deleteUser();
    }

}
