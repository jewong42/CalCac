package com.jewong.calcac.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@SuppressWarnings("unused")
@Entity(tableName = "user")
public class User {

    @PrimaryKey
    private int userId;
    private String name;
    private String gender;
    private String systemOfMeasurement;
    private int age;
    private int weight;
    private int height;
    private String diet;
    private String weightGoal;

    public User(String name, String gender, String systemOfMeasurement, int age, int weight, int height, String diet, String weightGoal) {
        this.userId = 0;
        this.name = name;
        this.gender = gender;
        this.systemOfMeasurement = systemOfMeasurement;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.diet = diet;
        this.weightGoal = weightGoal;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
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
