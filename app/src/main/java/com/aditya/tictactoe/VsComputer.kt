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

class VsComputer : AppCompatActivity(), View.OnClickListener{

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
                    text_view_p.text = p1+ " :0".padStart(10-p1.length)
                    text_view_comp.text= comp+" :0".padStart(10-p1.length)
//                    val intent = Intent(this, VsComputer::class.java)
//                    startActivity(intent)
                }
            }

        }
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
//Dialog Box ends
//        for (i in 0..2) {
//            for (j in 0..2) {
//                val buttonID = "button_$i$j"
//                val resID = resources.getIdentifier(buttonID, "id", packageName)
//                buttons[i][j] = findViewById(resID)
//                buttons[i][j]?.setOnClickListener(this)
//                buttons[i][j]?.isEnabled = false
//            }
//        }
//        val buttonReset = findViewById<Button>(R.id.button_reset)
//        buttonReset.setOnClickListener { resetGame() }

    }

    override fun onClick(v: View?) {
        if((v as Button).text == ""){
            return
        }
        if (player1Turn) {
            v.text = "X"
        }
        else{
            compPlays()
        }
        roundCount++
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                computerWins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        return if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            true
        } else false
    }

    private fun player1Wins() {
        player1Points++
        Toast.makeText(this, "$p1 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun computerWins() {
        computerPoints++
        Toast.makeText(this, "Computer wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        resetBoard()
    }

    private fun compPlays(){

        for (i in 0..2){
            for (j in 0..2){
                if (buttons[i][j]?.isEnabled==true){
                    buttons[i][j]?.text = "O"
                    buttons[i][j]?.isEnabled = false
                    break
                }
            }
            break
        }

    }



    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
                buttons[i][j]?.isEnabled=true
            }
        }
        roundCount = 0
        player1Turn = true

    }
    private fun resetGame() {
        player1Points = 0
        computerPoints = 0
        text_view_p.text = "$p1: 0"
        text_view_comp.text = "Computer: 0"
        updatePointsText()
        resetBoard()
    }
    private fun updatePointsText() {
        textViewPlayer1!!.text = "$p1: $player1Points"
        textViewComputer!!.text = "Computer: $computerPoints"
    }
}
