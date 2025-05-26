package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.data.Database
import com.example.database.data.dao.CardCollectionDao
import com.example.database.data.dao.InfoCardsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDi {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "bd"
        ).build()
    }

    @Provides
    fun provideInfoCardsDao(appDataBase: Database): InfoCardsDao {
        return appDataBase.infoCardsDao()
    }

    @Provides
    fun provideCardCollectionDao(appDataBase: Database): CardCollectionDao {
        return appDataBase.cardCollectionDao()
    }
}