package com.example.onlyburgerapp.presentation.orders_list_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.domain.repository.OnlyBurgerRepository
import com.example.onlyburgerapp.presentation.util.Routes
import com.example.onlyburgerapp.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersListViewModel @Inject constructor(
    private val repository: OnlyBurgerRepository,
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(OrdersListState())
    val state: StateFlow<OrdersListState> = _state.asStateFlow()

    private var deletedOrderList: OrderList? = null

//    init {
//        getOrders()
//    }

    fun getOrders() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            try {
                repository.getOrderLists()
                    .catch { e ->
                        Log.e("ajde", "Error: ${e.message}")
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = "Unknown error"
                            )
                        }
                    }
                    .collect { orders ->
                        Log.d("ajde","$orders")
                        _state.update {
                            it.copy(
                                isLoading = false,
                                orders = orders,
                                error = ""
                            )
                        }
                    }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "${e.localizedMessage}"
                    )
                }
            }
        }

    }

    fun onEvent(event: OrdersListEvent) {
        when (event) {
            is OrdersListEvent.OnOrderItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.orderDetailScreen + "?orderId=${event.orderList.id}"))
            }

            is OrdersListEvent.DeleteOrder -> {
                viewModelScope.launch {
                    deletedOrderList = event.orderList
                    repository.deleteOrderList(event.orderList)
                    sendUiEvent(UiEvent.ShowSnackbar("Order list deleted", "Undo"))
                }
            }

            is OrdersListEvent.OnAddOrderClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.orderDetailScreen))
            }


            is OrdersListEvent.OnUndoDeleteClick -> {
                deletedOrderList?.let { orderList ->
                    viewModelScope.launch {
                        repository.insertOrderList(orderList)
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}