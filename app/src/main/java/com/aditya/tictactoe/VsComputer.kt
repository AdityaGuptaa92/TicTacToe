package com.aditya.tictactoe

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vs_computer.*
import kotlinx.android.synthetic.main.activity_vs_friend.*

class VsComputer : AppCompatActivity(){

    private val buttons = Array(3) { arrayOfNulls<Button>(3) }
    private var player1Turn = true
    private var roundCount = 0
    private var player1Points = 0
    private var computerPoints = 0
    private var textViewPlayer1: TextView? = null  /*For updating score for player*/
    private var textViewComputer: TextView? = null /*For updating score for Computer*/
    private var p1=""
    private var comp="Computer"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_computer)
        textViewPlayer1 = findViewById(R.id.text_view_p)
        textViewComputer = findViewById(R.id.text_view_comp)

        //Dialog Box Starts
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.vscomputer_dialog,null)
        val playerName=dialogView.findViewById<EditText>(R.id.player1_name)
        dialog.setView(dialogView)
        dialog.setPositiveButton("Let's Play!!") { dialogInterface: DialogInterface, i: Int ->  }
        dialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->  }
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            when {
                playerName.text.isBlank() -> Toast.makeText(baseContext, "Please enter player name", Toast.LENGTH_LONG).show()
                playerName.text.length > 8 -> Toast.makeText(baseContext,"Player name too long!!",Toast.LENGTH_LONG).show()
                else -> {
                    customDialog.dismiss()
                    p1=playerName.text.toString()
                    textViewPlayer1!!.text = p1+ " :0".padStart(10-p1.length)
                    textViewComputer!!.text= comp+" :0".padStart(10-p1.length)
                }
            }

        }
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            val intent = Intent(this, Dashboard::class.java).apply {}
            startActivity(intent)
        }
        //Dialog Box ends
    }
}

