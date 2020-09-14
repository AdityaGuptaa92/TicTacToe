package com.aditya.tictactoe.vsfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R

class VsFriendHistory : AppCompatActivity() {

    private var player1Name : String = ""
    private var player2Name : String = ""
    private var player1Score : Int = 0
    private var player2Score : Int = 0
    private lateinit var friendHistoryDatas: List<FriendHistoryData>

    private lateinit var friendRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend_history)

        friendRecyclerView = findViewById(R.id.friend_recycler_view)
        friendRecyclerView.layoutManager = LinearLayoutManager(this)
        friendRecyclerView.setHasFixedSize(true)

        val intent = intent
        player1Name = intent.getStringExtra("PLAYER1_NAME")
        player2Name = intent.getStringExtra("PLAYER2_NAME")
        player1Score = intent.getIntExtra("PLAYER1_SCORE",0)
        player2Score = intent.getIntExtra("PLAYER2_SCORE",0)

        friendHistoryDatas = ArrayList()
        val friendHistoryData = FriendHistoryData(player1Name,player2Name,player1Score,player2Score)
        (friendHistoryDatas as ArrayList<FriendHistoryData>).add(friendHistoryData)
        val adapter = FriendHistoryAdapter(friendHistoryDatas)
        friendRecyclerView.adapter = adapter
    }
}