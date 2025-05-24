package com.example.onlyburgerapp.domain.util

import android.util.Log
import com.example.onlyburgerapp.data.OrderList

class PriceCalculator {
    companion object {
        fun priceCalculator(
            order: OrderList,
        ): Int {
            var price = 0
            order.burger?.forEach {
                Log.d("cenaje","${it.price}")
                price += it.price
            }
            order.sauce?.forEach {
                Log.d("cenaje","${it.price}")
                price += it.price
            }
            order.drink?.forEach {
                Log.d("cenaje","${it}")
                price += it.price
            }
            return price
        }
    }
}