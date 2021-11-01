package com.example.wallet.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallet.utils.Constant
import java.util.*


@Entity(tableName = WalletEntity.TABLE_NAME)
class WalletEntity {

    companion object {
        const val TABLE_NAME = "wallet_entity"
    }

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    @ColumnInfo
    var type: Int = Constant.PRODUCT_TYPE_DAY
    @ColumnInfo(name = "media_file")
    var day: Int = 0
    @ColumnInfo
    var hour: Int = 0
    @ColumnInfo
    var price: Float = 0F
    @ColumnInfo
    var activeState: Boolean = false
    @ColumnInfo
    var insertTime: Long = System.currentTimeMillis()
    @ColumnInfo
    var activeTime: Long = 0L
    @ColumnInfo
    var expireTime: Long = 0L

}