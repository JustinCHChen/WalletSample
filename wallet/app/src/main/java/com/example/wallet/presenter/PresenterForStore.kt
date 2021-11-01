package com.example.wallet.presenter

import com.example.wallet.utils.Constant
import com.example.wallet.model.ProductItem
import com.example.wallet.model.room.WalletEntity
import com.example.wallet.model.room.WalletRoomHelper
import com.example.wallet.view.IUpdateUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PresenterForStore : BasePresenter {

    constructor(updateUI: IUpdateUI) : super(updateUI)

    fun createMockData(): ArrayList<ProductItem> {
        var list = ArrayList<ProductItem>()
        list.add(ProductItem(Constant.PRODUCT_TYPE_DAY, 1, 0, 2.00F))
        list.add(ProductItem(Constant.PRODUCT_TYPE_DAY, 3, 0, 5.00F))
        list.add(ProductItem(Constant.PRODUCT_TYPE_DAY, 7, 0, 10.00F))
        list.add(ProductItem(Constant.PRODUCT_TYPE_HOUR, 0, 1, 0.50F))
        list.add(ProductItem(Constant.PRODUCT_TYPE_HOUR, 0, 8, 1.00F))
        list.add(ProductItem(Constant.PRODUCT_TYPE_HOUR, 0, 10, 1.10F))
        return list
    }

    @Synchronized
    fun doInsertItem(item: ProductItem) {
        GlobalScope.launch(Dispatchers.IO) {
            getContext()?.let { WalletRoomHelper.getInstance(it)?.WalletDao()?.insert(WalletEntity().apply {
                        type = item.type
                        day = item.day
                        hour = item.hour
                        price = item.price
                    }
                )
            }
        }
    }
}