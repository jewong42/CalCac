package com.jewong.calcac.common;

import android.text.Editable;
import android.text.TextWatcher;

public class SimpleTextWatcher implements TextWatcher {

    SimpleTextWatcher.Callback callback;

    public SimpleTextWatcher(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        callback.onTextChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface Callback {
        public void onTextChanged();
    }

}
