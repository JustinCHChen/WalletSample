package com.example.wallet.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun isWifi(context: Context): Boolean {
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connManager != null) {
            val info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (info != null) {
                return info.isConnected
            }
        }
        return false
    }

    fun isMobile(context: Context): Boolean {
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connManager != null) {
            val info = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (info != null) {
                return info.isConnected
            }
        }
        return false
    }
}