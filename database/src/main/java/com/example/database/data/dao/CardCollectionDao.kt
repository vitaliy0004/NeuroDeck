package com.example.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.database.entity.CollectionDeck
import com.example.database.entity.CollectionWithCards
import kotlinx.coroutines.flow.Flow


@Dao
interface CardCollectionDao {

    @Insert
    suspend fun addCollection(collectionDeck: CollectionDeck)

    @Query("DELETE FROM collectionDeck WHERE id = :id")
    suspend fun deleteCollectionByName(id: Int)

    @Transaction
    @Query("SELECT * FROM collectionDeck")
    fun getAllCollections(): Flow<List<CollectionWithCards>>

    @Update
    suspend fun updateCollection(collection: CollectionDeck)
}