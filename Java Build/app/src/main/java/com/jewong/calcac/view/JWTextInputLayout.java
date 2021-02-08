package com.jewong.calcac.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.jewong.calcac.R;
import com.jewong.calcac.databinding.ViewJwTextInputLayoutBinding;

public class JWTextInputLayout extends JWInputLayout {

    private ViewJwTextInputLayoutBinding mDataBinding;

    public JWTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.view_jw_text_input_layout,
                this,
                true);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        bindViews(mDataBinding.inputEditText, mDataBinding.inputLayout);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.JWTextInputLayout,
                0,
                0);
        try {
            mDataBinding.inputLayout.setHint(a.getString(R.styleable.JWTextInputLayout_android_hint));
            mDataBinding.inputEditText.setInputType(a.getInt(R.styleable.JWTextInputLayout_android_inputType, 0));
        } finally {
            a.recycle();
        }
    }

}
