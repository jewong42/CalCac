package com.jewong.calcac.data.stringdef;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        SystemOfMeasurement.IMPERIAL,
        SystemOfMeasurement.METRIC,
})
@Retention(RetentionPolicy.SOURCE)
public @interface SystemOfMeasurement {

    String IMPERIAL = "IMPERIAL";

    String METRIC = "METRIC";

}