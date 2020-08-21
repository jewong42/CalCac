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

    @Query("UPDATE user SET weight=:weight WHERE userId == 0")
    void updateWeight(Float weight);

    @Query("UPDATE user SET diet=:diet WHERE userId == 0")
    void updateDiet(String diet);

    @Query("UPDATE user SET weightGoal=:goal WHERE userId == 0")
    void updateGoal(String goal);

}