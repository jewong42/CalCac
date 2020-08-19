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

public class ProfileFormViewModel extends AndroidViewModel {

    public MutableLiveData<String> mNameInput = new MutableLiveData<>("");
    public MutableLiveData<String> mGenderInput = new MutableLiveData<>("");
    public MutableLiveData<String> mSystemInput = new MutableLiveData<>("");
    public MutableLiveData<String> mAgeInput = new MutableLiveData<>("");
    public MutableLiveData<String> mHeightInput = new MutableLiveData<>("");
    public LiveData<Integer> mHeightHint = Transformations.map(mSystemInput, this::getHeightHint);
    public MutableLiveData<String> mWeightInput = new MutableLiveData<>("");
    public LiveData<Integer> mWeightHint = Transformations.map(mSystemInput, this::getWeightHint);
    public MediatorLiveData<Boolean> mIsFormCompleted = new MediatorLiveData<>();

    public ProfileFormViewModel(@NonNull Application application) {
        super(application);
        Observer<String> formObserver = value -> mIsFormCompleted.setValue(
                ProfileFormViewModel.this.isFormFilled() && ProfileFormViewModel.this.isFormValid());
        mIsFormCompleted.addSource(mNameInput, formObserver);
        mIsFormCompleted.addSource(mNameInput, formObserver);
        mIsFormCompleted.addSource(mGenderInput, formObserver);
        mIsFormCompleted.addSource(mAgeInput, formObserver);
        mIsFormCompleted.addSource(mSystemInput, formObserver);
        mIsFormCompleted.addSource(mHeightInput, formObserver);
        mIsFormCompleted.addSource(mWeightInput, formObserver);
    }

    private Integer getHeightHint(String system) {
        switch (system) {
            case SystemOfMeasurement.METRIC:
                return R.string.height_hint_metric;
            case SystemOfMeasurement.IMPERIAL:
                return R.string.height_hint_imperial;
            default:
                return R.string.height_hint;
        }
    }

    private Integer getWeightHint(String system) {
        switch (system) {
            case SystemOfMeasurement.METRIC:
                return R.string.weight_hint_metric;
            case SystemOfMeasurement.IMPERIAL:
                return R.string.weight_hint_imperial;
            default:
                return R.string.weight_hint;
        }
    }

    private boolean isFormValid() {
        return isFormFilled();
    }

    private boolean isFormFilled() {
        return !isNullOrBlank(mNameInput.getValue())
                && !isNullOrBlank(mGenderInput.getValue())
                && !isNullOrBlank(mSystemInput.getValue())
                && !isNullOrBlank(mAgeInput.getValue())
                && !isNullOrBlank(mHeightInput.getValue())
                && !isNullOrBlank(mWeightInput.getValue());
    }

    private boolean isNullOrBlank(String string) {
        return string == null || string.length() == 0;
    }

}
