package com.aditya.tictactoe.vsfriend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R

class PlayerHistoryAdapter : RecyclerView.Adapter<PlayerHistoryAdapter.PlayerHistoryHolder>() {
    private var histories: List<PlayerHistory> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHistoryHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_history_item,parent,false)
        return PlayerHistoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerHistoryHolder, position: Int) {
        val currentHistory : PlayerHistory = histories[position]
        holder.textViewPriority.text = currentHistory.getPriority().toString()
        holder.textViewPlayer1.text = currentHistory.getPlayer1()
        holder.textViewPlayer2.text = currentHistory.getPlayer2()
        holder.textViewScore.text = currentHistory.getScore()
        holder.textViewWhoWon.text = currentHistory.getWhoWon()
    }

    override fun getItemCount(): Int {
        return histories.size
    }

    fun setHistory(histories: List<PlayerHistory>) {
        this.histories = histories
        notifyDataSetChanged()
    }
    class PlayerHistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewPriority : TextView = itemView.findViewById(R.id.text_view_priority)
        var textViewPlayer1: TextView = itemView.findViewById(R.id.text_view_player_one_name)
        var textViewPlayer2: TextView = itemView.findViewById(R.id.text_view_player_second_name)
        var textViewScore: TextView = itemView.findViewById(R.id.text_view_score)
        var textViewWhoWon: TextView = itemView.findViewById(R.id.text_view_player_won)

    }
}