package com.example.tictactao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var board: Array<Array<Button>>
    private var playturn=true
    private var playcount=0
    private var backgroundIn=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        board=Array(3){r->
            Array(3){c->
                initButton(r,c)
            }
        }
        reset.setOnClickListener {
            reset(board)
        }
        changebackground.setOnClickListener {
            if (backgroundIn) {

                background.setImageResource(R.drawable.cover1)
                backgroundIn = !backgroundIn
            } else {

                background.setImageResource(R.drawable.cover2)
                backgroundIn = !backgroundIn
            }
        }
    }

    fun reset(board: Array<Array<Button>>) {
        for (i in 0 .. 2){
            for(j in 0 .. 2){
                board[i][j].setBackgroundResource(R.drawable.normalbackground)
                board[i][j].text=""
            }
        }
        playcount=0
    }

    fun initButton(r:Int,c:Int):Button{
        val Button:Button=findViewById(resources.getIdentifier("Button$r$c","id",packageName))
        Button.setOnClickListener{
            onbuttonclick(Button)
        }
        return Button
    }

    private fun onbuttonclick(Button: Button) {
        if(Button.text!="")return
        if(playturn){
            Button.text="X"
        }else{
            Button.text="O"
        }
        playcount++
        winner(board)
        if (playcount == 9) {
            for (i in 0 .. 2){
                for(j in 0 .. 2){
                    board[i][j].setBackgroundResource(R.drawable.drowbackground)
                }
            }
        }else{
            playturn=!playturn
        }
    }
    fun winner(board:Array<Array<Button>>):Boolean {
        for(j in 0 ..2){
            if((board[j][0].text==board[j][1].text)&&(board[j][2].text==board[j][1].text)&&(board[j][0].text!="")){
                board[j][0].setBackgroundResource(R.drawable.winnerbackground)
                board[j][1].setBackgroundResource(R.drawable.winnerbackground)
                board[j][2].setBackgroundResource(R.drawable.winnerbackground)
                return true
            }
        }
        for(j in 0 .. 2){
            if((board[0][j].text==board[1][j].text)&&(board[1][j].text==board[2][j].text)&&(board[0][j].text!="")){
                board[0][j].setBackgroundResource(R.drawable.winnerbackground)
                board[1][j].setBackgroundResource(R.drawable.winnerbackground)
                board[2][j].setBackgroundResource(R.drawable.winnerbackground)
                return true
            }
        }
            if((board[0][0].text==board[1][1].text)&&(board[1][1].text==board[2][2].text)&&(board[1][1].text!="")){
                board[0][0].setBackgroundResource(R.drawable.winnerbackground)
                board[1][1].setBackgroundResource(R.drawable.winnerbackground)
                board[2][2].setBackgroundResource(R.drawable.winnerbackground)
                return true
            }
            if((board[0][2].text==board[1][1].text)&&(board[1][1].text==board[2][0].text)&&(board[1][1].text!="")){
                board[0][2].setBackgroundResource(R.drawable.winnerbackground)
                board[1][1].setBackgroundResource(R.drawable.winnerbackground)
                board[2][0].setBackgroundResource(R.drawable.winnerbackground)
                return true
            }
        return false
    }
}