package com.websarva.quizapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var numberOfQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        //画面が開いたら
        //QuizActivityから渡されたデータをゲット
        val bundle = intent.extras
        numberOfQuestion = bundle!!.getInt("numberOfQuestion")
        intCorrect = bundle!!.getInt("intCorrect")

        //問題数を表示
        textViewNumberOfQuestion.text = numberOfQuestion.toString()

        //正解数を表示
        textViewCorrect.text = intCorrect.toString()

        //結果を判定
        if (intCorrect >= numberOfQuestion -5) {
            textViewResultMessage.text = "CLEAR!!"
            textViewResultMessage.setTextColor(Color.BLUE)
        } else {
            textViewResultMessage.text = "FAILURE..."
            textViewResultMessage.setTextColor(Color.RED)
        }

        //TRY AGAINボタンでMainActivityへ遷移
        buttonTryAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}