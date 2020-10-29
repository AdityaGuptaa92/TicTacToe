package com.aditya.tictactoe.vsfriend

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import com.aditya.tictactoe.adapters.FriendHistoryAdapter
import com.aditya.tictactoe.homepage.Dashboard
import com.aditya.tictactoe.homepage.SharedPrefForNightMode
import com.aditya.tictactoe.vsfriend.frienddb.BaseActivity
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryDao
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryData
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryDatabase
import kotlinx.coroutines.launch


class VsFriendHistory : BaseActivity() {
    private lateinit var sharedPrefForNightMode: SharedPrefForNightMode
    private lateinit var friendHistoryWholeData: List<FriendHistoryData>
    private lateinit var friendRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPrefForNightMode = SharedPrefForNightMode(this)
        if (sharedPrefForNightMode.loadNightModeState()) {
            setTheme(R.style.DarkTheme)
        } else setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend_history)

        friendRecyclerView = findViewById(R.id.friend_history_recycler_view)
        friendRecyclerView.layoutManager = LinearLayoutManager(this)
        friendRecyclerView.setHasFixedSize(true)
        
        launch{
            lateinit var friendHistoryDao: FriendHistoryDao
            val database: FriendHistoryDatabase? = application?.let {
                FriendHistoryDatabase.getInstance(it)
            }
            if (database != null) {
                friendHistoryDao = database.friendHistoryDao()
            }
            friendHistoryWholeData = friendHistoryDao.getWholeHistory()
            friendRecyclerView.adapter = FriendHistoryAdapter(friendHistoryWholeData,friendHistoryDao,application)
            if (friendHistoryWholeData.isEmpty()) {
                Toast.makeText(baseContext, "No history to show", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,Dashboard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}