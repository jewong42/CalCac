package com.jewong.calcac.common;

import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.data.stringdef.Gender;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;

public final class MathUtils {

    public static float getCalories(User user) {
        if (user == null) return 0f;
        int weight = user.getWeight();
        int height = user.getHeight();
        int age = user.getAge();
        String gender = user.getGender();
        float weightMultiplier =
                user.getSystemOfMeasurement().equals(SystemOfMeasurement.IMPERIAL) ? 2.20462f : 1f;
        float heightMultiplier =
                user.getSystemOfMeasurement().equals(SystemOfMeasurement.IMPERIAL) ? 0.39370f : 1f;
        float f1 = (10.0f * weight) / weightMultiplier;
        float f2 = (6.25f * height) / heightMultiplier;
        float f3 = 5.00f * age;
        if (gender.equals(Gender.MALE)) {
            return roundToOneDecimal(f1 + f2 - f3 + 5f);
        } else {
            return roundToOneDecimal(f1 + f2 - f3 - 161f);
        }
    }

    public static float getFats(User user) {
        if (user == null) return 0f;
        float calories = getCalories(user);
        return roundToOneDecimal((calories / 9f) * 0.30f);
    }

    public static float getCarbohydrates(User user) {
        if (user == null) return 0f;
        float calories = getCalories(user);
        return roundToOneDecimal((calories / 4f) * 0.45f);
    }

    public static float getProtein(User user) {
        if (user == null) return 0f;
        float calories = getCalories(user);
        return roundToOneDecimal((calories / 4f) * 0.45f);
    }

    private static float roundToOneDecimal(float f) {
        return Math.round(f * 10.0f) / 10.0f;
    }

}