package com.example.wallet.presenter

import com.example.wallet.model.room.WalletEntity
import com.example.wallet.model.room.WalletRoomHelper
import com.example.wallet.utils.Constant
import com.example.wallet.utils.UpdateUIReason
import com.example.wallet.view.IUpdateUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PresenterForWallet : BasePresenter {

    private var mDataList: ArrayList<WalletEntity> = ArrayList()

    constructor(updateUI: IUpdateUI) : super(updateUI)

    @Synchronized
    fun getAllItems() {
        mDataList.clear()
        GlobalScope.launch(Dispatchers.IO) {
            getContext()?.let { WalletRoomHelper.getInstance(it)?.WalletDao()?.getAll() }?.let {
                mDataList.addAll(it)
            }
            withContext(Dispatchers.Main) {
                updateUI(UpdateUIReason.UPDATE_UI_QUERY_WALLET_LIST)
            }
        }
    }

    fun getDataSet(): ArrayList<WalletEntity> {
        return mDataList
    }

    fun doActivate(item: WalletEntity) {
        item.activeState = true
        item.activeTime = System.currentTimeMillis()
        item.expireTime = getExpireDate(item)
        updateItem(item)
    }

    private fun updateItem(item: WalletEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            getContext()?.let { WalletRoomHelper.getInstance(it)?.WalletDao()?.update(item) }
            withContext(Dispatchers.Main) {
                updateUI(UpdateUIReason.UPDate_UI_UPDATE_WALLET_LIST)
            }
        }
    }

    private fun getExpireDate(item: WalletEntity): Long {
        return if (item.type == Constant.PRODUCT_TYPE_DAY) {
            val activateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.activeTime)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date: Date = dateFormat.parse(activateTime)
            val millis: Long = date.time
            millis + item.day * Constant.TIME_DAY
        } else {
            item.activeTime + (item.hour * Constant.TIME_HOUR)
        }
    }
}