package com.example.wallet.presenter

import android.content.Context
import com.example.wallet.utils.UpdateUIReason
import com.example.wallet.view.IUpdateUI
import java.lang.ref.WeakReference

abstract class BasePresenter {
    private var mWeakUpdateUI: WeakReference<IUpdateUI>


    constructor(updateUI: IUpdateUI) {
        mWeakUpdateUI = WeakReference(updateUI)
    }

    fun getContext(): Context? {
        var ui: IUpdateUI? = getUpdateUI()
        return ui?.getContext()
    }

    fun updateUI(uiReason: UpdateUIReason) {
        var ui: IUpdateUI? = getUpdateUI()
        ui?.updateUI(uiReason)
    }

    private fun getUpdateUI(): IUpdateUI? {
        return mWeakUpdateUI?.get()
    }

    open fun destroy(){
        mWeakUpdateUI.clear()
    }
}
