package com.aditya.tictactoe.vsfriend

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayerHistoryDao {

    @Insert
    fun insert(playerHistory: PlayerHistory)

    @Update
    fun update(playerHistory: PlayerHistory)

    @Delete
    fun delete(playerHistory: PlayerHistory)

    @Query("DELETE FROM history_table")
    fun deleteWholeHistory()

    @Query("SELECT * FROM history_table ORDER BY priority ASC")
    fun getWholeHistory(): LiveData<List<PlayerHistory>>


}