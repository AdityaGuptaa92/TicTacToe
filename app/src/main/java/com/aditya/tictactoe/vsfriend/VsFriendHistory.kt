package com.aditya.tictactoe.vsfriend

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R


class VsFriendHistory : AppCompatActivity() {

    private var player1Name: String = ""
    private var player2Name: String = ""
    private var player1Score: Int = 0
    private var player2Score: Int = 0
    private lateinit var friendHistoryDatas: List<FriendHistoryData>

    private lateinit var friendRecyclerView: RecyclerView


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend_history)

        friendRecyclerView = findViewById(R.id.friend_recycler_view)
        friendRecyclerView.layoutManager = LinearLayoutManager(this)
        friendRecyclerView.setHasFixedSize(true)

        val mPreferences: SharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        player1Name = mPreferences.getString("PLAYER1_NAME", "").toString()
        player2Name = mPreferences.getString("PLAYER2_NAME", "").toString()
        player1Score = mPreferences.getInt("PLAYER1_SCORE", 0)
        player2Score = mPreferences.getInt("PLAYER2_SCORE", 0)


        friendHistoryDatas = ArrayList()
        val friendHistoryData = FriendHistoryData(
            player1Name,
            player2Name,
            player1Score,
            player2Score
        )
        (friendHistoryDatas as ArrayList<FriendHistoryData>).add(friendHistoryData)
        val adapter = FriendHistoryAdapter(friendHistoryDatas)
        friendRecyclerView.adapter = adapter
    }
}