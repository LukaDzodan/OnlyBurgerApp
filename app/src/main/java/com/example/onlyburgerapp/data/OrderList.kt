package com.example.onlyburgerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.domain.model.Drink
import com.example.onlyburgerapp.domain.model.Sauce

@Entity
data class OrderList(
    val burger : List<Burger>? = emptyList(),
    val sumPrice : Int ?= 0 ,
    val adress : String ?= "",
    val sauce : List<Sauce>? = emptyList(),
    val drink : List<Drink>? = emptyList(),
    val description : String? ?= "",
    @PrimaryKey val id : Int? = null
)
