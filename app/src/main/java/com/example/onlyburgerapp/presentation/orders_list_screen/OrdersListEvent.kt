package com.example.onlyburgerapp.presentation.orders_list_screen

import com.example.onlyburgerapp.data.OrderList

sealed class OrdersListEvent {
    data class DeleteOrder(val orderList: OrderList) : OrdersListEvent()
    object OnUndoDeleteClick : OrdersListEvent()
    data class OnOrderItemClick(val orderList: OrderList) : OrdersListEvent()
    object  OnAddOrderClick : OrdersListEvent()
}


