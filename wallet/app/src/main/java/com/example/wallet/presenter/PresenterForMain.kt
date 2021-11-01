package com.example.wallet.presenter

import com.example.wallet.model.IRequestListener
import com.example.wallet.model.MigoData
import com.example.wallet.model.MigoRequester
import com.example.wallet.utils.UpdateUIReason
import com.example.wallet.view.IUpdateUI

class PresenterForMain : BasePresenter, IRequestListener {

    private var mMigoRequester: MigoRequester? = null

    constructor(updateUI: IUpdateUI) : super(updateUI) {
        this.mMigoRequester = MigoRequester(this)
    }

    fun getStatus() {
        getContext()?.let { mMigoRequester?.doMigoGetStatus(it)?.executeGet(it) }
    }

    override fun onRequestSuccess(reason: UpdateUIReason) {
        updateUI(reason)
    }

    override fun onRequestFail() {
        updateUI(UpdateUIReason.UPDATE_UI_REASON_REQUEST_FAIL)
    }

    fun getResponse() : MigoData? {
        return mMigoRequester?.getResponseData()
    }

    override fun destroy() {
        super.destroy()
        mMigoRequester?.destroy()
        mMigoRequester = null
    }
}