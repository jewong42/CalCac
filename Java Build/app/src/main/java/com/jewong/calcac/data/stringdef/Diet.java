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

    String LOW_CARB = "LOW_CARB";

    String PALEO = "PALEO";

    String KETO = "KETO";

    String TRADITIONAL = "TRADITIONAL";

}