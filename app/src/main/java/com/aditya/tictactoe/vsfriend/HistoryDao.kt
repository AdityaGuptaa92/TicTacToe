package com.aditya.tictactoe.vsfriend

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HistoryDao {

    @Insert
    fun insert(history: History)

    @Update
    fun update(history: History)

    @Delete
    fun delete(history: History)

    @Query("DELETE FROM history_table")
    fun deleteWholeHistory()

    @Query("SELECT * FROM history_table ORDER BY id ASC")
    fun getWholeHistory(): LiveData<List<History>>


}