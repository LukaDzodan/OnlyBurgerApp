package com.example.onlyburgerapp.presentation.confirm_order_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.data.convertors.Converters
import com.example.onlyburgerapp.data.dto.OrderListDto
import com.example.onlyburgerapp.data.dto.toOrderList
import com.example.onlyburgerapp.data.dto.toOrderListDto
import com.example.onlyburgerapp.domain.repository.OnlyBurgerRepository
import com.example.onlyburgerapp.presentation.util.Routes
import com.example.onlyburgerapp.presentation.util.UiEvent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.net.URLDecoder
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class ConfirmOrderListViewModel @Inject constructor(
    private val repository: OnlyBurgerRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var orderList by mutableStateOf<OrderList?>(null)
        private set

    var isNotEmpty by mutableStateOf<Boolean>(false)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val type: Type? = object : TypeToken<OrderListDto>() {}.type
    //kada se vratis on racuna iznova(kada dodas burger opet), ne radi ti chessy, i kad dodas vise odjednom budu jedan preko drugog
    init {
        val encodedOrder = savedStateHandle.get<String>("order")
        Log.d("ConfirmOrderViewModel", "Received order: $encodedOrder")

        if (encodedOrder.isNullOrEmpty()) {
            isNotEmpty = false
        } else {
            try {
                val decodedOrder = URLDecoder.decode(encodedOrder, "UTF-8")
                val orderListToTake = Gson().fromJson<OrderListDto>(decodedOrder, type)
                orderList = orderListToTake.toOrderList()
                Log.d("orderr1", "$orderList")
                isNotEmpty = !orderList?.burger.isNullOrEmpty() || !orderList?.drink.isNullOrEmpty() || !orderList?.sauce.isNullOrEmpty()
            } catch (e: Exception) {
                Log.e("ConfirmOrderViewModel", "Error parsing order: ${e.message}")
                isNotEmpty = false
            }
        }
    }

    fun AddOrder() {
        if (isNotEmpty == true) {
            viewModelScope.launch {
                repository.insertOrderList(orderList!!)
            }
            sendUiEvent(UiEvent.Navigate(Routes.ordersListScreen))
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}