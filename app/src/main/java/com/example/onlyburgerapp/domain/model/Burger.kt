package com.example.onlyburgerapp.domain.model

import com.example.onlyburgerapp.domain.util.BurgerCalculator.Companion.calculatePrice

sealed class Burger(val burgerName: String, open val price: Int) {
    open fun getSizeOfMeat(): BurgerSize? = null

    data class ClassicBurger(val numberOfMeat: BurgerSize) :
        Burger(
            burgerName = "ClassicBurger",
            price = calculatePrice("ClassicBurger", numberOfMeat)
        ) {
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    data class OriginalBurger(val numberOfMeat: BurgerSize) : Burger(
        burgerName = "OriginalBurger",
        price = calculatePrice("OriginalBurger", numberOfMeat)
    ){
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    data class TruffleMayoBurger(val numberOfMeat: BurgerSize) : Burger(
        burgerName = "TruffleMayoBurger",
        price = calculatePrice("TruffleMayoBurger", numberOfMeat)
    ){
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    data class BaconBurger(val numberOfMeat: BurgerSize) :
        Burger(burgerName = "BaconBurger", price = calculatePrice("BaconBurger", numberOfMeat)){
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    data class JalapenoBurger(val numberOfMeat: BurgerSize) : Burger(
        burgerName = "JalapenoBurger",
        price = calculatePrice("JalapenoBurger", numberOfMeat)
    ){
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    data class ChessBurger(val numberOfMeat: BurgerSize) :
        Burger(
            burgerName = "ChessyBurger",
            price = calculatePrice("ChessyBurger", numberOfMeat)
        ){
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    data class CaramelBurger(val numberOfMeat: BurgerSize) :
        Burger(
            burgerName = "CaramelBurger",
            price = calculatePrice("CaramelBurger", numberOfMeat)
        ){
        override fun getSizeOfMeat(): BurgerSize? = numberOfMeat
    }

    object OnlyBurger : Burger(burgerName = "OnlyBurger", price = 790)
    object FatBoyBurger : Burger(burgerName = "FatBoyBurger", price = 710)
}