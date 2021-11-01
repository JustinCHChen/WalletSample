package com.example.wallet.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.wallet.utils.MyRequestManager
import com.example.wallet.utils.UpdateUIReason
import org.json.JSONObject
import java.lang.ref.WeakReference

abstract class BaseRequester : Response.Listener<JSONObject>, Response.ErrorListener {

    protected lateinit var url: String
    private var mCallback: WeakReference<IRequestListener>

    constructor(callback: IRequestListener) {
        this.mCallback = WeakReference(callback)
    }

    fun executePost(context: Context) {
        var jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, null, this, this)
        MyRequestManager.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun executeGet(context: Context) {
        var jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, this, this)
        MyRequestManager.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    protected fun onSuccess(reason: UpdateUIReason) {
        getCallback()?.onRequestSuccess(reason)
    }

    protected fun onFail() {
        getCallback()?.onRequestFail()
    }

    private fun getCallback(): IRequestListener? {
        return mCallback?.get()
    }

    public open fun destroy() {
        mCallback.clear()
    }
}