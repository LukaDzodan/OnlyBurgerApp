package com.example.onlyburgerapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.onlyburgerapp.data.convertors.Converters

@Database(
    entities = [OrderList::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class OnlyBurgerDatabase : RoomDatabase() {

    abstract val dao : OnlyBurgerDao

}