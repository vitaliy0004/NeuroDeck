package com.example.database.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.dao.CardCollectionDao
import com.example.database.data.dao.InfoCardsDao
import com.example.database.entity.CollectionDeck
import com.example.database.entity.InfoCards

@Database(
    entities = [CollectionDeck::class, InfoCards::class],
    version = 1,
    exportSchema = true,
)
abstract class Database : RoomDatabase() {
    abstract fun cardCollectionDao(): CardCollectionDao
    abstract fun infoCardsDao(): InfoCardsDao
}