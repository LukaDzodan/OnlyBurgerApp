package com.example.onlyburgerapp.domain.util

import com.example.onlyburgerapp.domain.model.BurgerSize

class BurgerCalculator {
    companion object {
        fun calculatePrice(type: String, numberOfMeat: BurgerSize): Int {
            return when (type) {
                "ClassicBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                "OriginalBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                "TruffleMayoBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                "BaconBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                "JalapenoBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                "ChessyBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                "CaramelBurger" -> when (numberOfMeat) {
                    BurgerSize.Single -> 490
                    BurgerSize.Double -> 630
                    BurgerSize.Tripple -> 750
                }
                else -> 0
            }
        }
    }
}