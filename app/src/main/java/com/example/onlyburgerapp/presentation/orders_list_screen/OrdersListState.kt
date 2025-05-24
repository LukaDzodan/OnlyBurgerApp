package com.example.onlyburgerapp.presentation.orders_list_screen

import com.example.onlyburgerapp.data.OrderList

data class OrdersListState(
    val orders : List<OrderList>  = emptyList(),
    val error : String = "",
    val isLoading : Boolean = false,
)
