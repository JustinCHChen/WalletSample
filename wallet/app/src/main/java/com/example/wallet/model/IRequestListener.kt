package com.example.wallet.model

import com.example.wallet.utils.UpdateUIReason

interface IRequestListener {
    fun onRequestSuccess(reason: UpdateUIReason)

    fun onRequestFail()
}