package com.jewong.calcac.data.stringdef;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        Diet.LOW_CARB,
        Diet.PALEO,
        Diet.KETO,
        Diet.TRADITIONAL,
})
@Retention(RetentionPolicy.SOURCE)
public @interface Diet {

    String LOW_CARB = "Low carb";

    String PALEO = "Paleo";

    String KETO = "Keo";

    String TRADITIONAL = "Traditional";

}