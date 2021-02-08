package com.jewong.calcac.view;

import android.content.Context;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.jewong.calcac.R;
import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.databinding.ViewJwTextViewLayoutBinding;

public class JWTextView extends LinearLayout {

    private ViewJwTextViewLayoutBinding mDataBinding;

    public JWTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.view_jw_text_view_layout,
                this,
                true);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.JWTextView,
                0,
                0);
        try {
            mDataBinding.label.setText(a.getString(R.styleable.JWTextView_label));
            mDataBinding.label.setTextAppearance(context, a.getResourceId(R.styleable.JWTextView_labelTextAppearance, 0));
            mDataBinding.text.setTextAppearance(context, a.getResourceId(R.styleable.JWTextView_textAppearance, 0));
            if (!StringUtils.isNullOrBlank(a.getString(R.styleable.JWTextView_textSuffix))) {
                mDataBinding.textSuffix.setVisibility(View.VISIBLE);
                mDataBinding.textSuffix.setText(a.getString(R.styleable.JWTextView_textSuffix));
                mDataBinding.textSuffix.setTextAppearance(context, a.getResourceId(R.styleable.JWTextView_textSuffixAppearance, 0));
            } else {
                mDataBinding.textSuffix.setVisibility(View.GONE);
            }
        } finally {
            a.recycle();
        }
    }

    @BindingAdapter("text")
    public static void setText(JWTextView layout, String text) {
        String t = (text != null) ? text : "";
        if (!layout.mDataBinding.text.getText().toString().equals(t)) {
            layout.mDataBinding.text.setText(t);
        }
    }

}

