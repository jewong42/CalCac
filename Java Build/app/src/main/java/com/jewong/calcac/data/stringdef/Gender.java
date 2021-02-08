package com.jewong.calcac.data.stringdef;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        Gender.MALE,
        Gender.FEMALE,
        Gender.OTHER
})
@Retention(RetentionPolicy.SOURCE)
public @interface Gender {

    String MALE = "MALE";

    String FEMALE = "FEMALE";

    String OTHER = "OTHER";

}