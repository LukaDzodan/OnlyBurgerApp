package com.example.onlyburgerapp.presentation.util

import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.domain.model.BurgerSize
import com.example.onlyburgerapp.domain.model.Drink
import com.example.onlyburgerapp.domain.model.Sauce

object MenuData {

    val burgers = listOf(
        Burger.ClassicBurger(BurgerSize.Single),
        Burger.OnlyBurger,
        Burger.JalapenoBurger(BurgerSize.Single),
        Burger.OriginalBurger(BurgerSize.Single),
        Burger.BaconBurger(BurgerSize.Single),
        Burger.CaramelBurger(BurgerSize.Single),
        Burger.ChessBurger(BurgerSize.Single),
        Burger.FatBoyBurger,
        Burger.TruffleMayoBurger(BurgerSize.Single),
    )

    val drinks = listOf(
        Drink.CocaCola(150),
        Drink.Fanta(150),
        Drink.Schweppes(150),
        Drink.Cockta(150)
    )

    val sauces = listOf (
        Sauce.CreamSauce(90),
        Sauce.KetchupSauce(90),
        Sauce.MayonnaiseSauce(90),
        Sauce.SweetChillySauce(90),
        Sauce.TruffleMayoSauce(90),
        Sauce.BBQSauce(90),
        Sauce.HomeMadeBurgerSauce(90),
        Sauce.HoneyMustardSauce(90),
    )
}