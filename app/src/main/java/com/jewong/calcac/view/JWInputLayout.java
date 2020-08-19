package com.jewong.calcac.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.textfield.TextInputLayout;
import com.jewong.calcac.common.SimpleTextWatcher;
import com.jewong.calcac.common.SimpleTextWatcher.Callback;

public class JWInputLayout extends TextInputLayout {

    EditText mEditText;

    public void bindEditText(EditText editText) {
        mEditText = editText;
    }

    public JWInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @BindingAdapter("android:textAttrChanged")
    public static void setTextListener(
            JWInputLayout layout,
            final InverseBindingListener listener
    ) {
        layout.mEditText.addTextChangedListener(new SimpleTextWatcher(new Callback() {
            @Override
            public void onTextChanged() {
                listener.onChange();
            }
        }));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static String getText(JWInputLayout layout) {
        return layout.mEditText.getText().toString();
    }

    @BindingAdapter("android:text")
    public static void setText(JWInputLayout layout, String text) {
        String t = (text != null) ? text : "";
        if (!layout.mEditText.getText().toString().equals(t)) {
            layout.mEditText.setText(t);
        }
    }

}
