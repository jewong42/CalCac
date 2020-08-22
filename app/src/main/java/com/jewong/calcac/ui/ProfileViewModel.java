package com.jewong.calcac.ui;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Application;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.jewong.calcac.R;
import com.jewong.calcac.common.MathUtils;
import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;
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
    public MutableLiveData<String> mDietValue = new MutableLiveData<>("");
    public MutableLiveData<String> mGoalValue = new MutableLiveData<>("");
    public MutableLiveData<String> mDietInput = new MutableLiveData<>("");
    public MutableLiveData<String> mGoalInput = new MutableLiveData<>("");
    public LiveData<Integer> mWeightHint;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mProfileRepository = new ProfileRepository(application);
        mUser = mProfileRepository.getUser();
        mWeightHint = Transformations.map(mUser, this::getWeightHint);
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
        mDietValue.setValue(mUser.getValue().getDiet());
        mGoalValue.setValue(mUser.getValue().getWeightGoal());
    }

    public void updateDiet() {
        String diet = mDietValue.getValue();
        mProfileRepository.updateDiet(diet);
    }

    public void updateGoal() {
        String goal = mGoalValue.getValue();
        mProfileRepository.updateGoal(goal);
    }

    public void updateWeight() {
        String weight = mWeightInput.getValue();
        mProfileRepository.updateWeight(weight);
    }

    private Integer getWeightHint(User user) {
        if (user == null || StringUtils.isNullOrBlank(user.getSystemOfMeasurement())) return R.string.weight_hint;
        switch (user.getSystemOfMeasurement()) {
            case SystemOfMeasurement.METRIC:
                return R.string.weight_hint_metric;
            case SystemOfMeasurement.IMPERIAL:
                return R.string.weight_hint_imperial;
            default:
                return R.string.weight_hint;
        }
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
