package com.example.onlyburgerapp.domain.model

sealed class Drink(val name: String) {
    abstract val price : Int
    data class CocaCola(override val price: Int = 150) : Drink("Coca Cola")
    data class Fanta(override val price: Int = 150) : Drink("Fanta")
    data class Schweppes(override val price: Int = 150) : Drink("Schweppes")
    data class Cockta(override val price: Int = 150) : Drink("Cockta")
}