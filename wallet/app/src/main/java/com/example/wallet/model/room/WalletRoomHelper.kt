package com.example.wallet.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [(WalletEntity::class)], version = 1)
abstract class WalletRoomHelper : RoomDatabase() {

    abstract fun WalletDao(): WalletDao

    companion object {
        private const val DATABASE_NAME = "wallet_db"
        private var INSTANCE: WalletRoomHelper? = null

        fun getInstance(context: Context): WalletRoomHelper? {
            if (INSTANCE == null) {
                synchronized(WalletRoomHelper::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        WalletRoomHelper::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}