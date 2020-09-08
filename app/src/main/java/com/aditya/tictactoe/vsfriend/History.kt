package com.aditya.tictactoe.vsfriend

import android.media.AsyncPlayer
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history_table")
class History {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    private var player1: String

    private var player2: String

    private var player1Score: Int = 0

    private var player2Score: Int = 0

    constructor(player1: String, player2: String, player1Score: Int, player2Score: Int) {
        this.player1 = player1
        this.player2 = player2
        this.player1Score = player1Score
        this.player2Score = player2Score
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId() = id

    fun getPlayer1() = player1

    fun getPlayer2() = player2

    fun getPlayer1Score() = player1Score

    fun getPlayer2Score() = player2Score
}