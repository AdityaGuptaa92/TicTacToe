package com.aditya.tictactoe.vsfriend.frienddb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FriendHistoryDao {

    @Insert
    fun addHistory(friendHistoryData: FriendHistoryData)

    @Query("SELECT * FROM friend_history ORDER BY id DESC")
    fun getWholeHistory() : List<FriendHistoryData>

    @Delete
    fun deleteHistory(friendHistoryData: FriendHistoryData)


}