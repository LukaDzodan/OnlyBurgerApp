package com.example.onlyburgerapp.domain.repository

import com.example.onlyburgerapp.data.OrderList
import kotlinx.coroutines.flow.Flow

interface OnlyBurgerRepository {

    suspend fun insertOrderList( orderList: OrderList)

    suspend fun deleteOrderList(orderList: OrderList)

    suspend fun getOrderListById(id : Int) : OrderList?

    suspend fun getOrderLists() : Flow<List<OrderList>>
}