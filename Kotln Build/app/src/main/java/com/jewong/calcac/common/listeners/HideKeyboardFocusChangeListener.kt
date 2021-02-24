package com.jewong.calcac.common.listeners

import android.view.View
import com.jewong.calcac.common.BaseFragment

class KeyboardFocusChangeListener(
    private val fragment: BaseFragment<*>
): View.OnFocusChangeListener {

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) fragment.hideSoftKeyBoard(v) else fragment.showSoftKeyBoard(v)
    }

}