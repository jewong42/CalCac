package com.jewong.calcac.common;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

public class BaseFragment<DB> extends Fragment {

    public DB mDataBinding;

    @Override
    public void onDestroyView() {
        mDataBinding = null;
        super.onDestroyView();
    }

    protected void hideSoftKeyBoard() {
        if (getActivity() == null) return;
        View view = getActivity().getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
