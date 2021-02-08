package com.jewong.calcac.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jewong.calcac.common.AppExecutors;
import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.data.entity.User;

public class ProfileRepository {

    private UserDao mUserDao;
    private LiveData<User> mUser;

    public ProfileRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mUserDao = db.userDao();
        mUser = mUserDao.loadUser();
    }

    public LiveData<User> getUser() {
        return mUser;
    }

    public void insert(User user) {
        if (user == null) return;
        AppExecutors.getInstance().diskIO().execute(() -> mUserDao.insertUser(user));
    }

    public void deleteUser() {
        AppExecutors.getInstance().diskIO().execute(() -> mUserDao.deleteUser());
    }

    public void updateWeight(String weight) {
        if (StringUtils.isNullOrBlank(weight) || mUser.getValue() == null) return;
        Float f = Float.valueOf(weight);
        if (!f.equals(mUser.getValue().getWeight())) {
            AppExecutors.getInstance().diskIO().execute(() -> mUserDao.updateWeight(f));
        }
    }

    public void updateDiet(String diet) {
        if (StringUtils.isNullOrBlank(diet) || mUser.getValue() == null) return;
        if (!diet.equals(mUser.getValue().getDiet())) {
            AppExecutors.getInstance().diskIO().execute(() -> mUserDao.updateDiet(diet));
        }
    }

    public void updateGoal(String goal) {
        if (StringUtils.isNullOrBlank(goal) || mUser.getValue() == null) return;
        if (!goal.equals(mUser.getValue().getWeightGoal())) {
            AppExecutors.getInstance().diskIO().execute(() -> mUserDao.updateGoal(goal));
        }
    }
}
