package com.example.wallet.model

import com.google.gson.annotations.SerializedName

data class MigoData(
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("message")
        var message: String? = null,
)