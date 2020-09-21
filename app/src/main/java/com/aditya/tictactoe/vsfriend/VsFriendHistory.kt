package com.aditya.tictactoe.vsfriend

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryDao
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryData
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryDatabase


class VsFriendHistory : AppCompatActivity() {

    private lateinit var friendHistoryWholeData: List<FriendHistoryData>
    private lateinit var friendRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend_history)

        friendRecyclerView = findViewById(R.id.friend_recycler_view)
        friendRecyclerView.layoutManager = LinearLayoutManager(this)
        friendRecyclerView.setHasFixedSize(true)
        showWholeHistory()
    }

    private fun showWholeHistory() {
        class ShowWholeHistory : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                lateinit var friendHistoryDao: FriendHistoryDao
                val database: FriendHistoryDatabase? = application?.let {
                    FriendHistoryDatabase.getInstance(it)
                }
                if (database != null) {
                    friendHistoryDao = database.friendHistoryDao()
                }
                friendHistoryWholeData = friendHistoryDao.getWholeHistory()
                friendRecyclerView.adapter = FriendHistoryAdapter(friendHistoryWholeData)
                return null
            }
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                if (friendHistoryWholeData.isEmpty()) {
                    Toast.makeText(baseContext, "No history to show", Toast.LENGTH_SHORT).show()
                }
            }
        }
        ShowWholeHistory().execute()
    }
}