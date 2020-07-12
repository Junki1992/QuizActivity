package com.websarva.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*
import kotlin.math.log

class QuizActivity : AppCompatActivity() {

    val questionList = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //Questionクラスをインスタンス化
        val question1 = Question()
        question1.answer1 = "ドナルド・トランプ"
        question1.answer2 = "ジョージ・ワシントン"
        question1.answer3 = "エイブラハム・リンカーン"

        val question2 = Question()
        question2.answer1 = "ジョン・F・ケネディ"
        question2.answer2 = "ジョージ・W・ブッシュ"
        question2.answer3 = "バラク・オバマ"

        val question3 = Question()
        question3.answer1 = "ジェームズ・モンロー"
        question3.answer2 = "アンドリュー・ジャクソン"
        question3.answer3 = "トーマス・ジェファーソン"

        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)


            //選択肢が押されたら
        buttonAnswer1.setOnClickListener {
            xxx(1)
        }

        buttonAnswer2.setOnClickListener {
            xxx(2)
        }

        buttonAnswer3.setOnClickListener {
            xxx(3)
        }

        //「BACK」ボタンが押されたら
        buttonBack.setOnClickListener {
            finish()
        }

        //setQuestionメソッドの呼び出し
        setQuestion()
    }

    private fun xxx(yyy: Int) {
        if (yyy == 2) {
            imageViewAnswer.setImageResource(R.drawable.pic_correct)
        } else {
            imageViewAnswer.setImageResource(R.drawable.pic_incorrect)
        }

        setQuestion()
    }

    private fun setQuestion() {

        val random = Random()
        val questionIndex = random.nextInt(questionList.count())
        Log.d("questionIndex", questionIndex.toString())

        imageView.setImageResource(R.drawable.george_washington)

        val question = questionList[questionIndex]

        //question1の選択肢
        buttonAnswer1.text = question.answer1
        buttonAnswer2.text = question.answer2
        buttonAnswer3.text = question.answer3
    }
}
