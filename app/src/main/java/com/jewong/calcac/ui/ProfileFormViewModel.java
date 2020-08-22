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
import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;
import com.jewong.calcac.model.ProfileRepository;

public class ProfileFormViewModel extends AndroidViewModel {

    private ProfileRepository mProfileRepository;
    public LiveData<User> mUser;
    public MutableLiveData<String> mNameInput = new MutableLiveData<>("");
    public MutableLiveData<String> mGenderValue = new MutableLiveData<>("");
    public MutableLiveData<String> mSystemValue = new MutableLiveData<>("");
    public MutableLiveData<String> mGenderInput = new MutableLiveData<>("");
    public MutableLiveData<String> mSystemInput = new MutableLiveData<>("");
    public MutableLiveData<String> mAgeInput = new MutableLiveData<>("");
    public MutableLiveData<String> mHeightInput = new MutableLiveData<>("");
    public MutableLiveData<String> mWeightInput = new MutableLiveData<>("");
    public LiveData<Integer> mHeightHint = Transformations.map(mSystemValue, this::getHeightHint);
    public LiveData<Integer> mWeightHint = Transformations.map(mSystemValue, this::getWeightHint);
    public MediatorLiveData<Boolean> mIsFormCompleted = new MediatorLiveData<>();

    public ProfileFormViewModel(@NonNull Application application) {
        super(application);
        mProfileRepository = new ProfileRepository(application);
        mUser = mProfileRepository.getUser();
        initFormMediator();
    }

    private void initFormMediator() {
        Observer<String> formObserver = value -> mIsFormCompleted.setValue(
                ProfileFormViewModel.this.isFormFilled());
        mIsFormCompleted.addSource(mNameInput, formObserver);
        mIsFormCompleted.addSource(mNameInput, formObserver);
        mIsFormCompleted.addSource(mGenderValue, formObserver);
        mIsFormCompleted.addSource(mAgeInput, formObserver);
        mIsFormCompleted.addSource(mSystemValue, formObserver);
        mIsFormCompleted.addSource(mHeightInput, formObserver);
        mIsFormCompleted.addSource(mWeightInput, formObserver);
    }

    public void saveUserAndClearCache() {
        User user = getUserFromFactory();
        clearCache();
        mProfileRepository.insert(user);
    }

    @SuppressWarnings("ConstantConditions")
    private User getUserFromFactory() {
        try {
            return new User(
                    mNameInput.getValue(),
                    mGenderValue.getValue(),
                    mSystemValue.getValue(),
                    Integer.parseInt(mAgeInput.getValue()),
                    Float.parseFloat(mWeightInput.getValue()),
                    Float.parseFloat(mHeightInput.getValue()),
                    null,
                    null);
        } catch (Exception e) {
            return null;
        }
    }

    public void clearCache() {
        mNameInput.setValue("");
        mGenderInput.setValue("");
        mSystemInput.setValue("");
        mAgeInput.setValue("");
        mHeightInput.setValue("");
        mWeightInput.setValue("");
    }

    private Integer getHeightHint(String system) {
        if (StringUtils.isNullOrBlank(system)) return R.string.height_hint;
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
        if (StringUtils.isNullOrBlank(system)) return R.string.weight_hint;
        switch (system) {
            case SystemOfMeasurement.METRIC:
                return R.string.weight_hint_metric;
            case SystemOfMeasurement.IMPERIAL:
                return R.string.weight_hint_imperial;
            default:
                return R.string.weight_hint;
        }
    }

    private boolean isFormFilled() {
        return !StringUtils.isNullOrBlank(mNameInput.getValue())
                && !StringUtils.isNullOrBlank(mGenderValue.getValue())
                && !StringUtils.isNullOrBlank(mSystemValue.getValue())
                && !StringUtils.isNullOrBlank(mAgeInput.getValue())
                && !StringUtils.isNullOrBlank(mHeightInput.getValue())
                && !StringUtils.isNullOrBlank(mWeightInput.getValue());
    }

}
