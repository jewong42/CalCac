package com.jewong.calcac.common;

import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.data.stringdef.Diet;
import com.jewong.calcac.data.stringdef.Gender;
import com.jewong.calcac.data.stringdef.Goal;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;

public final class MathUtils {

    public static float getCalories(User user) {
        float baseCalories = getBaseCalories(user);
        float genderModifier = user.getGender().equals(Gender.MALE) ? 5f : -161f;
        float goalModifier = 0f;
        switch (user.getWeightGoal()) {
            case Goal.WEIGHT_GAIN:
                goalModifier = 200f;
                break;
            case Goal.WEIGHT_LOSS:
                goalModifier = -200f;
                break;
        }
        return roundToOneDecimal(baseCalories + genderModifier + goalModifier);
    }

    public static float getFats(User user) {
        if (user == null) return 0f;
        float calories = getCalories(user);
        float multiplier = 1f;
        switch (user.getDiet()) {
            case Diet.KETO:
                multiplier = 0.70f;
                break;
            case Diet.LOW_CARB:
                multiplier = 0.40f;
                break;
            case Diet.PALEO:
                multiplier = 0.35f;
                break;
            case Diet.TRADITIONAL:
                multiplier = 0.15f;
                break;
        }
        return roundToOneDecimal((calories * multiplier) / 9f);
    }

    public static float getCarbohydrates(User user) {
        if (user == null) return 0f;
        float calories = getCalories(user);
        float multiplier = 1f;
        switch (user.getDiet()) {
            case Diet.KETO:
                multiplier = 0.05f;
                break;
            case Diet.LOW_CARB:
                multiplier = 0.20f;
                break;
            case Diet.PALEO:
                multiplier = 0.40f;
                break;
            case Diet.TRADITIONAL:
                multiplier = 0.60f;
                break;
        }
        return roundToOneDecimal((calories * multiplier) / 4);
    }

    public static float getProtein(User user) {
        if (user == null) return 0f;
        float calories = getCalories(user);
        float multiplier = 1f;
        switch (user.getDiet()) {
            case Diet.KETO:
                multiplier = 0.25f;
                break;
            case Diet.LOW_CARB:
                multiplier = 0.40f;
                break;
            case Diet.PALEO:
                multiplier = 0.25f;
                break;
            case Diet.TRADITIONAL:
                multiplier = 0.25f;
                break;
        }
        return roundToOneDecimal((calories * multiplier) / 4f);
    }

    public static float roundToOneDecimal(float f) {
        return Math.round(f * 10.0f) / 10.0f;
    }

    private static float getBaseCalories(User user) {
        if (user == null) return 0f;
        float weight = user.getWeight();
        float height = user.getHeight();
        int age = user.getAge();
        float weightMultiplier =
                user.getSystemOfMeasurement().equals(SystemOfMeasurement.IMPERIAL) ? 2.20462f : 1f;
        float heightMultiplier =
                user.getSystemOfMeasurement().equals(SystemOfMeasurement.IMPERIAL) ? 0.39370f : 1f;
        float f1 = (10.0f * weight) / weightMultiplier;
        float f2 = (6.25f * height) / heightMultiplier;
        float f3 = 5.00f * age;
        return roundToOneDecimal(f1 + f2 - f3);
    }

}