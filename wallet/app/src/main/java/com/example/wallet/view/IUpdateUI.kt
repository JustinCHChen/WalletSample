package com.example.wallet.view

import android.content.Context
import com.example.wallet.utils.UpdateUIReason

interface IUpdateUI {
    fun getContext(): Context?

    fun updateUI(type: UpdateUIReason)

}