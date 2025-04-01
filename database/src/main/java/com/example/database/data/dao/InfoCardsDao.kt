package com.example.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.database.entity.InfoCards
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoCardsDao {
    @Insert
    suspend fun addCard(card: InfoCards)

    @Query("SELECT * FROM infoCards WHERE collection_id = :collectionId")
    fun getCardsForCollection(collectionId: Int): Flow<List<InfoCards>>

    @Query("DELETE FROM infoCards WHERE question = :question AND id = :id")
    suspend fun deleteCard(question: String, id: Int)

    @Update
    suspend fun updateCard(card: InfoCards)
}