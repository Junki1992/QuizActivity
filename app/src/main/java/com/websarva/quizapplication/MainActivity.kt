package com.websarva.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //選択肢を入れるためのArrayAdapterをセット
        val arrayAdapter = ArrayAdapter<Int>(this,android.R.layout.simple_spinner_item).apply {
            add(1)  //テスト用
            add(15)
            add(25)
            add(45)
        }

        //spinnerとAdapter（選択肢）を繋ぐ
        spinner.adapter = arrayAdapter

        //「スタート」ボタンを押してQuizActivityへ（spinnerで選択した問題数を渡す）
        buttonStart.setOnClickListener {

            //選択した問題数をゲット
            val numberOfQuestion: Int = spinner.selectedItem.toString().toInt()

            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("numberOfQuestion", numberOfQuestion)
            startActivity(intent)
        }
    }
}
