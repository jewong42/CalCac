package com.jewong.calcac.ui;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Application;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jewong.calcac.common.MathUtils;
import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.model.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    final static String PROPERTY_NAME = "value";

    private ProfileRepository mProfileRepository;
    public LiveData<User> mUser;

    public MutableLiveData<Float> mCalories = new MutableLiveData<>(0f);
    public MutableLiveData<Float> mFat = new MutableLiveData<>(0f);
    public MutableLiveData<Float> mCarbohydrates = new MutableLiveData<>(0f);
    public MutableLiveData<Float> mProtein = new MutableLiveData<>(0f);
    public MutableLiveData<String> mWeightInput = new MutableLiveData<>("");
    public MutableLiveData<String> mDietInput = new MutableLiveData<>("");
    public MutableLiveData<String> mGoalInput = new MutableLiveData<>("");

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mProfileRepository = new ProfileRepository(application);
        mUser = mProfileRepository.getUser();
    }

    public void deleteProfile() {
        mProfileRepository.deleteUser();
    }

    public void updateData() {
        if (mUser.getValue() == null) return;
        animateToValue(mCalories, MathUtils.getCalories(mUser.getValue()));
        animateToValue(mFat, MathUtils.getFats(mUser.getValue()));
        animateToValue(mCarbohydrates, MathUtils.getCarbohydrates(mUser.getValue()));
        animateToValue(mProtein, MathUtils.getProtein(mUser.getValue()));
        mWeightInput.setValue(String.valueOf(mUser.getValue().getWeight()));
        mDietInput.setValue(mUser.getValue().getDiet());
        mGoalInput.setValue(mUser.getValue().getWeightGoal());
    }

    public void updateDiet() {
        String diet = mDietInput.getValue();
        mProfileRepository.updateDiet(diet);
    }

    public void updateGoal() {
        String goal = mGoalInput.getValue();
        mProfileRepository.updateGoal(goal);
    }

    public void updateWeight() {
        String weight = mWeightInput.getValue();
        mProfileRepository.updateWeight(weight);
    }

    private void animateToValue(MutableLiveData<Float> liveData, Float finalValue) {
        Float initialValue = liveData.getValue() != null ? liveData.getValue() : 0f;
        long duration = (long) Math.min(600, Math.abs(((initialValue - finalValue) * 10)));
        PropertyValuesHolder value = PropertyValuesHolder.ofFloat(
                PROPERTY_NAME,
                initialValue,
                finalValue);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setValues(value);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            Object o = animation.getAnimatedValue(PROPERTY_NAME);
            Float f = MathUtils.roundToOneDecimal((Float) o);
            liveData.setValue(f);
        });
        valueAnimator.start();
    }

}
