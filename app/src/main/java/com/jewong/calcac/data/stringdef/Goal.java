package com.jewong.calcac.data.stringdef;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        Goal.WEIGHT_LOSS,
        Goal.MAINTENANCE,
        Goal.WEIGHT_GAIN
})
@Retention(RetentionPolicy.SOURCE)
public @interface Goal {

    String WEIGHT_LOSS = "Weight loss";

    String MAINTENANCE = "Maintenance";

    String WEIGHT_GAIN = "Weight gain";

}