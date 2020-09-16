package com.aditya.tictactoe.vsfriend.frienddb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "friend_history")
data class FriendHistoryData(
    val playerOneName: String,
    val playerSecondName: String,
    val playerOneScore: Int,
    val playerSecondScore: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}