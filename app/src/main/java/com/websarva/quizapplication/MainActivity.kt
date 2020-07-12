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
        val arrayAdapter = ArrayAdapter<Int>(this,android.R.layout.simple_spinner_item)
        arrayAdapter.add(15)
        arrayAdapter.add(25)
        arrayAdapter.add(45)

        //spinnerとAdapter（選択肢）を繋ぐ
        spinner.adapter = arrayAdapter

        buttonStart.setOnClickListener {
            //「スタート」ボタンを押してQuizAcitivtyへ
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}
