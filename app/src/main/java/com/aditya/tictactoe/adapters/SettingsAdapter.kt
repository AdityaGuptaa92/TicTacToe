package com.aditya.tictactoe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R

class SettingsAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    private val settingsTitles = arrayOf("Theme", "Contact Us", "App Info")
    private val settingsIcons =
        arrayOf(R.drawable.ic_brightness96, R.drawable.ic_contact96, R.drawable.ic_info96)


    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val settingsIcon: ImageView = itemView.findViewById(R.id.settings_icon)
        val settingsItemName: TextView = itemView.findViewById(R.id.settings_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.settings_items, parent, false)
        return SettingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.settingsIcon.setImageResource(settingsIcons[position])
        holder.settingsItemName.text = settingsTitles[position]
    }

    override fun getItemCount() = settingsTitles.size
}