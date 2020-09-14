package com.aditya.tictactoe.vsfriend

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R

class FriendHistoryAdapter(private var friendHistoryData: List<FriendHistoryData>) : RecyclerView.Adapter<FriendHistoryAdapter.FriendHistoryHolder>() {

    class FriendHistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewPlayer1 = itemView.findViewById<TextView>(R.id.text_view_player_one_name)
        var textViewPlayer2 = itemView.findViewById<TextView>(R.id.text_view_player_second_name)
        var textViewScore = itemView.findViewById<TextView>(R.id.text_view_score)
        var textViewWhoWon = itemView.findViewById<TextView>(R.id.text_view_player_won)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHistoryHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_history_item,
                parent,false)
        return FriendHistoryHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FriendHistoryHolder, position: Int) {

        val currentFriendHistoryData = friendHistoryData[position]
        holder.textViewPlayer1.text = currentFriendHistoryData.getPlayerOneName()
        holder.textViewPlayer2.text = currentFriendHistoryData.getPlayerSecondName()
        holder.textViewScore.text = "Score: ${currentFriendHistoryData.getPlayerOneScore()}-${currentFriendHistoryData.getPlayerSecondScore()}"
        when {
            currentFriendHistoryData.getPlayerOneScore()>currentFriendHistoryData.getPlayerSecondScore() ->
                holder.textViewWhoWon.text = "${currentFriendHistoryData.getPlayerOneName()} won!"
            currentFriendHistoryData.getPlayerOneScore()<currentFriendHistoryData.getPlayerSecondScore() ->
                holder.textViewWhoWon.text = "${currentFriendHistoryData.getPlayerSecondName()} won!"
            else -> holder.textViewWhoWon.text = "Draw!"
        }

    }

    override fun getItemCount(): Int {
        return friendHistoryData.size

    }
}