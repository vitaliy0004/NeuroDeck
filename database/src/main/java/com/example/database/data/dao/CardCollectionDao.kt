package com.example.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.database.data.entity.CollectionDeckEntity
import com.example.database.data.entity.CollectionWithCardsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CardCollectionDao {

    @Insert
    suspend fun addCollection(collectionDeck: CollectionDeckEntity)

    @Query("DELETE FROM collectionDeck WHERE id = :id")
    suspend fun deleteCollectionByName(id: Int)

    @Transaction
    @Query("SELECT * FROM collectionDeck")
    fun getAllCollections(): Flow<List<CollectionWithCardsEntity>>

    @Update
    suspend fun updateCollection(collection: CollectionDeckEntity)
}