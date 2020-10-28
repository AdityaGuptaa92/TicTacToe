package com.aditya.tictactoe.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import com.aditya.tictactoe.homepage.Settings


class SettingsAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    private val settings = Settings()
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

        holder.settingsIcon.setOnClickListener {
            when (settingsIcons[position]) {
                R.drawable.ic_brightness96 -> Toast.makeText(mContext, "Theme", Toast.LENGTH_SHORT)
                    .show()
                R.drawable.ic_contact96 -> contactUs(mContext)
                R.drawable.ic_info96 -> appInfo(mContext)
            }
        }
    }

    override fun getItemCount() = settingsTitles.size

    private fun appInfo(context: Context) {
        val appInfoDialog = Dialog(context)
        appInfoDialog.setContentView(R.layout.app_info_popup)
        appInfoDialog.show()
        val closeAppInfo = appInfoDialog.findViewById<Button>(R.id.close_app_info)
        closeAppInfo.setOnClickListener {
            appInfoDialog.dismiss()
        }
    }

    private fun contactUs(context: Context) {
        val contactUsDialog = Dialog(context)
        contactUsDialog.setContentView(R.layout.contactus_popup)
        contactUsDialog.show()
        val githubLink = contactUsDialog.findViewById<ImageView>(R.id.github_link)
        val mailLink = contactUsDialog.findViewById<ImageView>(R.id.mail_link)
        val linkedInLink = contactUsDialog.findViewById<ImageView>(R.id.linked_link)
        val closeContactUs = contactUsDialog.findViewById<Button>(R.id.close_contactus)
        closeContactUs.setOnClickListener {
            contactUsDialog.dismiss()
        }

        githubLink.setOnClickListener {
            val url = "https://github.com/AdityaGuptaa92/TicTacToe"
            settings.openUrl(url)
        }

        mailLink.setOnClickListener {
            val url = "adityagupta3214@gmail.com"
            settings.openUrl(url)
        }

        linkedInLink.setOnClickListener {
            val url = "https://www.linkedin.com/in/aditya-gupta-646220191"
            settings.openUrl(url)
        }
    }
}