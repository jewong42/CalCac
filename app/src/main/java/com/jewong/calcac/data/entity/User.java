package com.jewong.calcac.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.data.stringdef.Diet;
import com.jewong.calcac.data.stringdef.Goal;

@SuppressWarnings("unused")
@Entity(tableName = "user")
public class User {

    @PrimaryKey
    private int userId;
    private String name;
    private String gender;
    private String systemOfMeasurement;
    private int age;
    private float weight;
    private float height;
    private String diet;
    private String weightGoal;


    public User(String name, String gender, String systemOfMeasurement, int age, float weight, float height, String diet, String weightGoal) {
        this.userId = 0;
        this.name = name;
        this.gender = gender;
        this.systemOfMeasurement = systemOfMeasurement;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.diet = StringUtils.isNullOrBlank(diet) ? Diet.TRADITIONAL : diet;
        this.weightGoal = StringUtils.isNullOrBlank(weightGoal) ? Goal.MAINTENANCE : weightGoal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSystemOfMeasurement() {
        return systemOfMeasurement;
    }

    public void setSystemOfMeasurement(String systemOfMeasurement) {
        this.systemOfMeasurement = systemOfMeasurement;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(String weightGoal) {
        this.weightGoal = weightGoal;
    }

}
