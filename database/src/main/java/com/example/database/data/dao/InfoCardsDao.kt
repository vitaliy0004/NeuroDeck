package com.example.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.database.data.entity.InfoCardsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoCardsDao {
    @Insert
    suspend fun addCard(card: InfoCardsEntity)

    @Query("SELECT * FROM infoCards WHERE collection_id = :collectionId")
    fun getCardsForCollection(collectionId: Int): Flow<List<InfoCardsEntity>>

    @Query("DELETE FROM infoCards WHERE id = :id")
    suspend fun deleteCard( id: Int)

    @Update
    suspend fun updateCard(card: InfoCardsEntity)
}