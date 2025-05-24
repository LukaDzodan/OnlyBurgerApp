package com.example.onlyburgerapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.onlyburgerapp.data.OnlyBurgerDatabase
import com.example.onlyburgerapp.data.repository.OnlyBurgerRepositoryImpl
import com.example.onlyburgerapp.domain.repository.OnlyBurgerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOnlyBurgerDatabase(app : Application) : OnlyBurgerDatabase {
            return Room.databaseBuilder(
                app,
                OnlyBurgerDatabase::class.java,
                "onlyburger_db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideOnlyBurgerRepository(db : OnlyBurgerDatabase) : OnlyBurgerRepository {
        return OnlyBurgerRepositoryImpl(db.dao)
    }
}