package com.example.wallet.model

import android.content.Context
import com.android.volley.VolleyError
import com.example.wallet.utils.NetworkUtil
import com.example.wallet.utils.UpdateUIReason
import com.google.gson.Gson
import org.json.JSONObject

class MigoRequester : BaseRequester {
    private val PUBLIC_URL = "https://code-test.migoinc-dev.com/status"
    private val PRIVATE_URL = "http://192.168.2.2/status"

    private var mResponseData: MigoData? = null


    constructor(requestListener: IRequestListener) : super(requestListener)

    fun doMigoGetStatus(context: Context): MigoRequester? {
        if (NetworkUtil.isWifi(context)) {
            this.url = PRIVATE_URL
            return this
        } else if (NetworkUtil.isMobile(context)) {
            this.url = PUBLIC_URL
            return this
        }
        return null
    }

    override fun onResponse(response: JSONObject) {
        try {
            val gson = Gson()
            mResponseData = gson.fromJson(response.toString(), MigoData::class.java)

            if (mResponseData?.status.equals("200")) {
                onSuccess(UpdateUIReason.UPDATE_UI_REASON_REQUEST_SUCCESS)
            } else {
                onFail()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            onFail()
        }
    }

    override fun onErrorResponse(error: VolleyError) {
        onFail()
    }

    fun getResponseData(): MigoData? {
        return mResponseData
    }

    override fun destroy() {
        super.destroy()
        mResponseData = null
    }
}