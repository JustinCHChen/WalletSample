package com.example.wallet.model.room

import androidx.room.*


@Dao
interface WalletDao {

    @Query("select * from " + WalletEntity.TABLE_NAME)
    fun getAll(): List<WalletEntity>

    @Query("select * from " + WalletEntity.TABLE_NAME + " where id LIKE :id")
    fun queryById(id: Long): WalletEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: WalletEntity)

    @Update
    fun update(item: WalletEntity)

    @Delete
    fun delete(item: WalletEntity)
}