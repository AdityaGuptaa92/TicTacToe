package com.aditya.tictactoe.vsfriend


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history_table")
class PlayerHistory(
    private var priority: Int,
    private var player1: String,
    private var player2: String,
    private var score:String,
    private var whoWon:String
) {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    fun setId(id: Int) {
        this.id = id
    }

    //Getters

    fun getId() = id

    fun getPriority() = priority

    fun getPlayer1() = player1

    fun getPlayer2() = player2

    fun getScore() = score

    fun getWhoWon() = whoWon
}