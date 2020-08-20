package com.jewong.calcac.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jewong.calcac.common.AppExecutors;
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
        AppExecutors.getInstance().diskIO().execute(() -> mUserDao.insertUser(user));
    }

    public void deleteUser() {
        AppExecutors.getInstance().diskIO().execute(() -> mUserDao.deleteUser());
    }

}
