package com.aditya.tictactoe.vsfriend.frienddb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FriendHistoryDao {

    @Insert
    suspend fun addHistory(friendHistoryData: FriendHistoryData)

    @Query("SELECT * FROM friend_history ORDER BY id DESC")
    suspend fun getWholeHistory() : List<FriendHistoryData>

    @Delete
    suspend fun deleteHistory(friendHistoryData: FriendHistoryData)
}