package com.example.onlyburgerapp.presentation.order_detail_screen

import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.domain.model.Drink
import com.example.onlyburgerapp.domain.model.Sauce


sealed class OrderDetailEvent {
    data class OnAdressChange(val adress: String) : OrderDetailEvent()
    data class OnDescriptionChange(val description : String) : OrderDetailEvent()
    object OnSaveOrderClick : OrderDetailEvent()
    data class OnAddBurgerClick(val burger : Burger) : OrderDetailEvent()
    data class OnAddDrinkClick(val drink : Drink) : OrderDetailEvent()
    data class OnAddSauceClick(val sauce : Sauce) : OrderDetailEvent()
}
