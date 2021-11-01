package com.example.wallet.view

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(), IFragmentState, IUpdateUI {
    override fun getContext(): Context? {
        return super.getContext()
    }
}