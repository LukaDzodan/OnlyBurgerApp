package com.example.onlyburgerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OnlyBurgerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderList( orderList: OrderList)

    @Delete
    suspend fun deleteOrderList(orderList: OrderList)

    @Query("SELECT * FROM OrderList WHERE id = :id")
    suspend fun getOrderListById(id : Int) : OrderList?

    @Query("SELECT * FROM OrderList")
    fun getOrderLists() : Flow<List<OrderList>>
}