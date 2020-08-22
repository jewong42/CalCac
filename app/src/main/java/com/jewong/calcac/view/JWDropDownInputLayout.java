package com.jewong.calcac.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.jewong.calcac.R;
import com.jewong.calcac.common.SimpleTextWatcher;
import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.databinding.ViewJwDropDownInputLayoutBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWDropDownInputLayout extends JWInputLayout {

    private ViewJwDropDownInputLayoutBinding mDataBinding;
    private HashMap<String, String> mHashMap = new HashMap<>();

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

    public void setItems(List<Pair<Integer, String>> items, Context context) {
        List<String> list = new ArrayList<>();
        for (Pair<Integer, String> entry : items) {
            String key = context.getString(entry.first);
            list.add(key);
            mHashMap.put(key, entry.second);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.list_item, list);
        mDataBinding.inputEditText.setAdapter(adapter);
    }

    @BindingAdapter("android:valueAttrChanged")
    public static void setValueListener(
            JWDropDownInputLayout layout,
            final InverseBindingListener listener
    ) {
        layout.mDataBinding.inputEditText.addTextChangedListener(new SimpleTextWatcher(listener::onChange));
    }

    @InverseBindingAdapter(attribute = "android:value")
    public static String getValue(JWDropDownInputLayout layout) {
        HashMap<String, String> map = layout.mHashMap;
        String key = layout.mEditText.getText().toString();
        if (map == null || !map.containsKey(key)) return "";
        else return map.get(key);
    }

    @SuppressWarnings("unused")
    @BindingAdapter("android:value")
    public static void setValue(JWDropDownInputLayout layout, String value) {
        HashMap<String, String> map = layout.mHashMap;
        String key = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
                break;
            }
        }
        if (!StringUtils.isNullOrBlank(key)) setText(layout, key);
    }

}
