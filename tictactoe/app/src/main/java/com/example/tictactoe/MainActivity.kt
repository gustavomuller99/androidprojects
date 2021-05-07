package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var buttons = arrayOf<Array<Button>>()
    private var player1turn: Boolean = true
    private var player1points: Int = 0
    private var player2points: Int = 0
    private var round: Int = 0
    private lateinit var player1: TextView
    private lateinit var player2: TextView
    private lateinit var reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player1 = findViewById(R.id.p1)
        player2 = findViewById(R.id.p2)
        reset = findViewById(R.id.reset)
        for (i in 0..2) {
            var row = arrayOf<Button>()
            for (j in 0..2) {
                val buttonId = "button_$i$j"
                val idObj = resources.getIdentifier(buttonId, "id", packageName)
                row += findViewById<Button>(idObj)
            }
            buttons += row
            for (j in 0..2) {
                buttons[i][j].setOnClickListener(this)
            }
        }
        reset.setOnClickListener(View.OnClickListener {
            player1points = 0
            player2points = 0
            player1.text = "Jogador 1: 0 pontos"
            player2.text = "Jogador 2: 0 pontos"
            clear()
        })
    }

    override fun onClick(v: View?) {
        val b: Button = v as Button
        if(b.text.toString() != "") {
            return
        }
        if(player1turn) b.text = "X"
        else b.text = "O"

        if(check()) {
            if(player1turn) player1.text = "Jogador 1: " + (++player1points).toString() + " pontos"
            else player2.text = "Jogador 2: " + (++player2points).toString() + " pontos"
            clear()
        }
        player1turn = !player1turn
        round++
        if(round == 9) clear()
    }

    private fun check(): Boolean {
        var countx: Int = 0; var counto: Int = 0; var win: Boolean = false
        for(i in 0..2) {
            countx = 0; counto = 0
            for(j in 0..2) {
                if(buttons[i][j].text == "O") counto++
                if(buttons[i][j].text == "X") countx++
            }
            if((player1turn && countx == 3) || (!player1turn && counto == 3)) win = true
        }
        for(j in 0..2) {
            countx = 0; counto = 0
            for(i in 0..2) {
                if(buttons[i][j].text == "O") counto++
                if(buttons[i][j].text == "X") countx++
            }
            if((player1turn && countx == 3) || (!player1turn && counto == 3)) win = true
        }
        countx = 0; counto = 0
        for(i in 0..2) {
            if(buttons[i][i].text == "O") counto++
            if(buttons[i][i].text == "X") countx++
        }
        if((player1turn && countx == 3) || (!player1turn && counto == 3)) win = true
        countx = 0; counto = 0
        for(i in 2 downTo 0) {
            if(buttons[i][2-i].text == "O") counto++
            if(buttons[i][2-i].text == "X") countx++
        }
        if((player1turn && countx == 3) || (!player1turn && counto == 3)) win = true
        return win
    }

    private fun clear() {
        round = 0
        for(i in 0..2)
            for(j in 0..2)
                buttons[i][j].text = ""
    }

}