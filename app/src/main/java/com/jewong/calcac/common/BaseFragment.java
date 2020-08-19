package com.jewong.calcac.common;

import androidx.fragment.app.Fragment;

public class BaseFragment<DB> extends Fragment {

    public DB mDataBinding;

    @Override
    public void onDestroyView() {
        mDataBinding = null;
        super.onDestroyView();
    }

}
