package com.jewong.calcac.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jewong.calcac.data.entity.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE userId == 0")
    LiveData<User> loadUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("DELETE FROM user WHERE userId == 0")
    void deleteUser();

}