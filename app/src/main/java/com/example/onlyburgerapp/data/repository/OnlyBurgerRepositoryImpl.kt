package com.example.onlyburgerapp.data.repository

import com.example.onlyburgerapp.data.OnlyBurgerDao
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.domain.repository.OnlyBurgerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnlyBurgerRepositoryImpl(
    private val dao : OnlyBurgerDao
) : OnlyBurgerRepository {
    override suspend fun insertOrderList(orderList: OrderList) {
        dao.insertOrderList(orderList)
    }

    override suspend fun deleteOrderList(orderList: OrderList) {
        dao.deleteOrderList(orderList)
    }

    override suspend fun getOrderListById(id: Int): OrderList? {
        return dao.getOrderListById(id)
    }

    override suspend fun getOrderLists(): Flow<List<OrderList>> {
        return dao.getOrderLists()
    }
}