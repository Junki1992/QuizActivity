package com.websarva.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*
import kotlin.math.log

class QuizActivity : AppCompatActivity() {

    val questionList = mutableListOf<Question>()
    val imageViewList = mutableListOf<Int>()

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

        //imageViewListをセット
        imageViewList.add(R.drawable.george_washington)
        imageViewList.add(R.drawable.john_adams)
        imageViewList.add(R.drawable.thomas_jefferson)
        imageViewList.add(R.drawable.james_madison)
        imageViewList.add(R.drawable.james_monroe)
        imageViewList.add(R.drawable.jqa)
        imageViewList.add(R.drawable.andrew_jackson)
        imageViewList.add(R.drawable.martin_van_buren)
        imageViewList.add(R.drawable.william_henry_harrison)
        imageViewList.add(R.drawable.john_tyler)


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
        //選択肢の乱数を生成
        val randomQuestion = Random()
        val questionIndex = randomQuestion.nextInt(questionList.count())
        Log.d("questionIndex", questionIndex.toString())

        imageView.setImageResource(R.drawable.george_washington)

        val question = questionList[questionIndex]

        //question1の選択肢
        buttonAnswer1.text = question.answer1
        buttonAnswer2.text = question.answer2
        buttonAnswer3.text = question.answer3

        //画像の乱数を生成
        val randomImage = Random()
        val imageViewIndex = randomImage.nextInt(imageViewList.count())
        Log.d("imageViewIndex", imageViewIndex.toString())

        val ImageView = imageViewList[imageViewIndex]
        imageView.setImageResource(ImageView)
    }
}
