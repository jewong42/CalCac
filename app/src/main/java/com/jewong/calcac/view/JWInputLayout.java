package com.jewong.calcac.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.textfield.TextInputLayout;
import com.jewong.calcac.common.SimpleTextWatcher;

public class JWInputLayout extends TextInputLayout {

    EditText mEditText;
    TextInputLayout mTextInputLayout;

    public void bindViews(EditText editText, TextInputLayout textInputLayout) {
        mEditText = editText;
        mTextInputLayout = textInputLayout;
    }

    public JWInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mEditText.setOnFocusChangeListener(l);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener l) {
        mEditText.setOnEditorActionListener(l);
    }

    @BindingAdapter("android:textAttrChanged")
    public static void setTextListener(
            JWInputLayout layout,
            final InverseBindingListener listener
    ) {
        layout.mEditText.addTextChangedListener(new SimpleTextWatcher(listener::onChange));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static String getText(JWInputLayout layout) {
        return layout.mEditText.getText().toString();
    }

    @BindingAdapter("android:text")
    public static void setText(JWInputLayout layout, String text) {
        String t = (text != null) ? text : "";
        if (!layout.mEditText.getText().toString().equals(t)) {
            if (layout.mEditText instanceof AutoCompleteTextView) {
                ((AutoCompleteTextView) layout.mEditText).setText(t, false);
            } else {
                layout.mEditText.setText(t);
            }
        }
    }

    @BindingAdapter("android:hint")
    public static void setHint(JWInputLayout layout, Integer resId) {
        Context context = layout.getContext();
        CharSequence hint = layout.mTextInputLayout.getHint();
        String t = (resId != null && resId != 0) ? context.getText(resId).toString() : "";
        String h = (hint != null) ? hint.toString() : "";
        if (!h.equals(t)) {
            layout.mTextInputLayout.setHint(t);
        }
    }

    @BindingAdapter("imeOptions")
    public static void setImeOption(JWInputLayout layout, int option) {
        layout.mEditText.setImeOptions(option);
    }

}
