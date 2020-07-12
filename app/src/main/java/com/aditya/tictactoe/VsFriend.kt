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
import kotlinx.android.synthetic.main.activity_vs_friend.*

class VsFriend : AppCompatActivity(){
    private var textViewPlayer1: TextView? = null
    private var textViewPlayer2: TextView? = null
    var p1=""
    var p2=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend)
        textViewPlayer1 = findViewById(R.id.text_view_p1)
        textViewPlayer2 = findViewById(R.id.text_view_p2)

        //DialogBox starts
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.vsfriend_dialog,null)
        //EditText from dialog box
        val player1 = dialogView.findViewById<EditText>(R.id.player1_name)
        val player2 = dialogView.findViewById<EditText>(R.id.player2_name)
        dialog.setView(dialogView)
        dialog.setPositiveButton("Let's Play!!") { Dialog: DialogInterface, i: Int ->}
        dialog.setNegativeButton("Cancel"){Dialog: DialogInterface, i: Int ->}
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            when {
                player1.text.isBlank() -> Toast.makeText(baseContext, "Please enter player 1 name", Toast.LENGTH_LONG).show()
                player1.length() > 8 -> Toast.makeText(baseContext, "Player 1 name too long!",Toast.LENGTH_LONG).show()
                player2.text.isBlank() -> Toast.makeText(baseContext, "Please enter player 2 name", Toast.LENGTH_LONG).show()
                player2.length() > 8 -> Toast.makeText(baseContext, "Player 2 name too long!",Toast.LENGTH_LONG).show()
                else -> {
                    customDialog.dismiss()
                    p1 = player1.text.toString()
                    p2 = player2.text.toString()
                    text_view_p1.text=p1+" :0".padStart(10-p1.length)
                    text_view_p2.text=p2+" :0".padStart(10-p2.length)
                }
            }
        }
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        //DialogBox Ends
    }
}




