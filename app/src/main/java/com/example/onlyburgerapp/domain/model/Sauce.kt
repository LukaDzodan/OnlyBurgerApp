package com.example.onlyburgerapp.domain.model

sealed class Sauce(val name: String) {
    abstract val price : Int
    data class HomeMadeBurgerSauce(override val price: Int = 90) :
        Sauce("Home made burger sauce")

    data class CreamSauce(override val price: Int = 90) : Sauce("Cream Sauce")
    data class HoneyMustardSauce(override val price: Int = 90) :
        Sauce("Honey mustard sauce")

    data class SweetChillySauce(override val price: Int = 90) :
        Sauce("Sweet chilly sauce")

    data class BBQSauce(override val price: Int = 90) : Sauce("BBQ sauce")
    data class TruffleMayoSauce(override val price: Int = 90) :
        Sauce("Truffle mayo sauce")

    data class KetchupSauce(override val price: Int = 90) : Sauce("Ketchup sauce")
    data class MayonnaiseSauce(override val price: Int = 90) : Sauce("Mayonnaise sauce")
}