package com.jewong.calcac.common.listeners

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.jewong.calcac.common.BaseFragment

class HideKeyboardEditorActionListener(
    private val fragment: BaseFragment<*>
) : TextView.OnEditorActionListener {

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_NEXT) fragment.hideSoftKeyBoard(v)
        return false
    }

}