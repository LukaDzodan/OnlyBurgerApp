package com.example.onlyburgerapp.data.convertors

import android.util.Log
import androidx.room.TypeConverter
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.data.dto.BurgerDto
import com.example.onlyburgerapp.data.dto.DrinkDto
import com.example.onlyburgerapp.data.dto.SauceDto
import com.example.onlyburgerapp.data.dto.toBurger
import com.example.onlyburgerapp.data.dto.toBurgerDto
import com.example.onlyburgerapp.data.dto.toDrink
import com.example.onlyburgerapp.data.dto.toDrinkDto
import com.example.onlyburgerapp.data.dto.toSauce
import com.example.onlyburgerapp.data.dto.toSauceDto
import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.domain.model.Drink
import com.example.onlyburgerapp.domain.model.Sauce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromBurgerList(value : List<Burger>?) : String {
        val dtoList = value?.map {it.toBurgerDto()}
        Log.d("dtoSer","$dtoList")
        return gson.toJson(dtoList)
    }

    @TypeConverter
    fun toBurgerList(value: String) : List<Burger>? {
        val type = object : TypeToken<List<BurgerDto>>() {}.type
        val dtoList : List<BurgerDto> = gson.fromJson(value,type)
        Log.d("dtoDeSer","$dtoList")
        return dtoList.map { it.toBurger() }
    }

    @TypeConverter
    fun fromDrinkList(value : List<Drink>?) : String {
        val dtoList = value?.map {it.toDrinkDto()}
        return gson.toJson(dtoList)
    }

    @TypeConverter
    fun toDrinkList(value: String) : List<Drink>? {
        val type = object : TypeToken<List<DrinkDto>>() {}.type
        val dtoList : List<DrinkDto> = gson.fromJson(value,type)
        return dtoList.map { it.toDrink() }
    }

    @TypeConverter
    fun fromSauceList(value : List<Sauce>?) : String {
        val dtoList = value?.map {it.toSauceDto()}
        return gson.toJson(dtoList)
    }

    @TypeConverter
    fun toSauceList(value: String) : List<Sauce>? {
        val type = object : TypeToken<List<SauceDto>>() {}.type
        val dtoList : List<SauceDto> = gson.fromJson(value,type)
        return dtoList.map { it.toSauce() }
    }

}