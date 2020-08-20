package com.jewong.calcac.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.jewong.calcac.R;
import com.jewong.calcac.databinding.ViewJwDropDownInputLayoutBinding;

import java.util.List;

public class JWDropDownInputLayout extends JWInputLayout {

    private ViewJwDropDownInputLayoutBinding mDataBinding;

    public JWDropDownInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.view_jw_drop_down_input_layout,
                this,
                true);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        bindViews(mDataBinding.inputEditText, mDataBinding.inputLayout);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.JWDropDownInputLayout,
                0,
                0);
        try {
            mDataBinding.inputLayout.setHint(a.getString(R.styleable.JWTextInputLayout_android_hint));
        } finally {
            a.recycle();
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setItems(List<String> items, Context context) {
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.list_item, items);
        mDataBinding.inputEditText.setAdapter(adapter);
    }

}
