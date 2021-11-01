package com.example.wallet.model

import com.example.wallet.utils.Constant

data class ProductItem(var type: Int = Constant.PRODUCT_TYPE_DAY, var day: Int = 0, var hour: Int = 0, var price: Float = 0F, var activeState: Boolean = false) {

}