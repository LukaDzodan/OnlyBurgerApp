package com.example.onlyburgerapp.presentation.order_detail_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.data.dto.toBurgerDto
import com.example.onlyburgerapp.data.dto.toOrderListDto
import com.example.onlyburgerapp.domain.repository.OnlyBurgerRepository
import com.example.onlyburgerapp.domain.util.PriceCalculator
import com.example.onlyburgerapp.presentation.util.Routes
import com.example.onlyburgerapp.presentation.util.UiEvent
import com.example.onlyburgerapp.presentation.util.UiEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.gson.Gson
import java.net.URLEncoder

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val repository: OnlyBurgerRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var orderList by mutableStateOf<OrderList?>(OrderList())
        private set

    var adress by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val orderId = savedStateHandle.get<Int>("orderId")!!
        if (orderId != -1) {
            viewModelScope.launch {
                repository.getOrderListById(orderId)?.let { order ->
                    adress = order.adress ?: ""
                    description = order.description ?: ""
                    this@OrderDetailViewModel.orderList = order
                }
            }
        }
    }


    fun onEvent(event: OrderDetailEvent) {

        when (event) {
            is OrderDetailEvent.OnAdressChange -> {
                adress = event.adress
                orderList = orderList?.copy(
                    adress = adress
                )
            }

            is OrderDetailEvent.OnDescriptionChange -> {
                description = event.description
                orderList = orderList?.copy(
                    description = description
                )
            }

            is OrderDetailEvent.OnSaveOrderClick -> {
                //Imas dto funk, primeni ovde pa u confirmu
                orderList = orderList?.copy(
                    sumPrice = PriceCalculator.priceCalculator(orderList!!)
                )
                val orderListToSand = orderList?.toOrderListDto()
                Log.e("ordermrtvi","$orderList")
                val jsonOrderList = Gson().toJson(orderListToSand)
                val encodedOrder = URLEncoder.encode(jsonOrderList,"UTF-8")
                //Log.e("ConfirmOrderViewModel", "Evo ga:$orderList")
                sendUiEvent(Navigate(Routes.confirmOrderScreen+"?order=$encodedOrder"))
            }

            is OrderDetailEvent.OnAddBurgerClick -> {
                orderList = orderList?.copy(
                    burger = orderList?.burger?.plus(event.burger)
                )
                Log.d("mojiburgeri","${orderList?.burger}")
                sendUiEvent(ShowSnackbar("Dodao si: ${orderList?.burger?.last()?.burgerName}"))
            }

            is OrderDetailEvent.OnAddDrinkClick -> {
                orderList = orderList?.copy(
                    drink = orderList?.drink?.plus(event.drink)
                )
                sendUiEvent(ShowSnackbar("Dodao si: ${orderList?.drink?.last()?.name}"))
            }
            is OrderDetailEvent.OnAddSauceClick -> {
                orderList = orderList?.copy(
                    sauce = orderList?.sauce?.plus(event.sauce)
                )
                sendUiEvent(ShowSnackbar("Dodao si: ${orderList?.sauce?.last()?.name}"))
            }
        }
        Log.e("myorder", "${orderList?.burger},${orderList?.adress},${orderList?.description}")

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}