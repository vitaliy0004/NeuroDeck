package com.example.database.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.dao.CardCollectionDao
import com.example.database.data.dao.InfoCardsDao
import com.example.database.data.entity.CollectionDeckEntity
import com.example.database.data.entity.InfoCardsEntity

@Database(
    entities = [CollectionDeckEntity::class, InfoCardsEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class Database : RoomDatabase() {
    abstract fun cardCollectionDao(): CardCollectionDao
    abstract fun infoCardsDao(): InfoCardsDao
}