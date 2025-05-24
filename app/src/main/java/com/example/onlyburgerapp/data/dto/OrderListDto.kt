package com.example.onlyburgerapp.data.dto

import android.util.Log
import androidx.room.PrimaryKey
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.domain.model.BurgerSize
import com.example.onlyburgerapp.domain.model.Drink
import com.example.onlyburgerapp.domain.model.Sauce
import com.google.gson.annotations.SerializedName
import kotlin.math.log

data class OrderListDto(
    val burger : List<BurgerDto>? = emptyList(),
    val sumPrice : Int ?= 0,
    val adress : String ?= "",
    val sauce : List<SauceDto>? = emptyList(),
    val drink : List<DrinkDto>? = emptyList(),
    val description : String? ?= "",
    @PrimaryKey val id : Int? = null
)

fun OrderList.toOrderListDto() : OrderListDto {
    return OrderListDto(
        burger = burger?.map { it.toBurgerDto() },
        sumPrice = sumPrice,
        adress = adress,
        sauce = sauce?.map { it.toSauceDto() },
        drink = drink?.map { it.toDrinkDto() },
        description = description,
        id = id
    )
}

fun OrderListDto.toOrderList() : OrderList {
    return OrderList(
        burger = burger?.map { it.toBurger() },
        sumPrice = sumPrice,
        adress = adress,
        sauce = sauce?.map { it.toSauce() },
        drink = drink?.map { it.toDrink() },
        description = description,
        id = id
    )
}
data class BurgerDto(
    @SerializedName("burgerName")
    val name : String,
    val numberOfMeat : String?
)
data class SauceDto(
    @SerializedName("name")
    val name : String,
    @SerializedName("price")
    val price : String?
)
data class DrinkDto(
    @SerializedName("name")
    val name : String,
    val price : String?
)

fun BurgerDto.toBurger() : Burger = when(name){
    "ClassicBurger" -> Burger.ClassicBurger(BurgerSize.valueOf(numberOfMeat!!))
    "OriginalBurger" -> Burger.OriginalBurger(BurgerSize.valueOf(numberOfMeat!!))
    "TruffleMayoBurger" -> Burger.TruffleMayoBurger(BurgerSize.valueOf(numberOfMeat!!))
    "BaconBurger" -> Burger.BaconBurger(BurgerSize.valueOf(numberOfMeat!!))
    "JalapenoBurger" -> Burger.JalapenoBurger(BurgerSize.valueOf(numberOfMeat!!))
    "ChessBurger" -> Burger.ChessBurger(BurgerSize.valueOf(numberOfMeat!!))
    "CaramelBurger" -> Burger.CaramelBurger(BurgerSize.valueOf(numberOfMeat!!))
    "OnlyBurger" -> Burger.OnlyBurger
    "FatBoyBurger" -> Burger.FatBoyBurger
    else -> throw IllegalArgumentException("Nepoznat burger: $name")
}

fun Burger.toBurgerDto() : BurgerDto = when(this){
    is Burger.BaconBurger -> BurgerDto("BaconBurger",numberOfMeat.name)
    is Burger.CaramelBurger -> BurgerDto("CaramelBurger",numberOfMeat.name)
    is Burger.ChessBurger -> BurgerDto("ChessBurger",numberOfMeat.name)
    is Burger.ClassicBurger -> BurgerDto("ClassicBurger",numberOfMeat.name)
    Burger.FatBoyBurger -> BurgerDto("FatBoyBurger",null)
    is Burger.JalapenoBurger -> BurgerDto("JalapenoBurger",numberOfMeat.name)
    Burger.OnlyBurger -> BurgerDto("OnlyBurger",null)
    is Burger.OriginalBurger -> BurgerDto("OriginalBurger",numberOfMeat.name)
    is Burger.TruffleMayoBurger -> BurgerDto("TruffleMayoBurger",numberOfMeat.name)
}

fun Drink.toDrinkDto() : DrinkDto {
    val price = when(this){
        is Drink.CocaCola -> this.price
        is Drink.Cockta -> this.price
        is Drink.Fanta -> this.price
        is Drink.Schweppes -> this.price
    }
    return DrinkDto(
        name = name,
        price = price.toString()
    )
}

fun DrinkDto.toDrink() : Drink {
    return when(name){
        "Coca Cola" -> Drink.CocaCola()
        "Fanta" -> Drink.Fanta()
        "Schweppes" -> Drink.Schweppes()
        "Cockta" -> Drink.Cockta()
        else -> throw IllegalArgumentException("Nepoznat napitak: $name")
    }
}

fun Sauce.toSauceDto() : SauceDto {
    Log.d("mojsos","$this")
    val price = when(this){
        is Sauce.BBQSauce -> this.price
        is Sauce.CreamSauce -> this.price
        is Sauce.HomeMadeBurgerSauce -> this.price
        is Sauce.HoneyMustardSauce -> this.price
        is Sauce.KetchupSauce -> this.price
        is Sauce.MayonnaiseSauce -> this.price
        is Sauce.SweetChillySauce -> this.price
        is Sauce.TruffleMayoSauce -> this.price
    }
    return SauceDto(
        name = name,
        price = this.price.toString()
    )
}

fun SauceDto.toSauce() : Sauce {


    return when(name){
        "Home made burger sauce" -> Sauce.HomeMadeBurgerSauce()
        "Cream Sauce" -> Sauce.CreamSauce()
        "Honey mustard sauce" -> Sauce.HoneyMustardSauce()
        "Sweet chilly sauce" -> Sauce.SweetChillySauce()
        "BBQ sauce" -> Sauce.BBQSauce()
        "Truffle mayo sauce" -> Sauce.TruffleMayoSauce()
        "Ketchup sauce" -> Sauce.KetchupSauce()
        "Mayonnaise sauce" -> Sauce.MayonnaiseSauce()
        else -> throw IllegalArgumentException("Nepoznat sos: $name")
    }
}

