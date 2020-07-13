package com.websarva.quizapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*

class QuizActivity : AppCompatActivity() {

    val questionList = mutableListOf<Question>()
    val imageViewList = mutableListOf<Int>()
    var answer = 0
//    lateinit var timer: Timer
    var numberOfRemaining = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //画面が開いたら

        //「NEXT」ボタンを無効化
        buttonNext.isEnabled = false

        //Questionクラスをインスタンス化
        val question1 = Question()
        question1.answer = 2

        question1.answer1 = "ドナルド・トランプ"
        question1.answer2 = "ジョージ・ワシントン"
        question1.answer3 = "エイブラハム・リンカーン"

        question1.imageResource = R.drawable.george_washington

        val question2 = Question()
        question2.answer = 1

        question2.answer1 = "ジョン・アダムス"
        question2.answer2 = "ジョージ・W・ブッシュ"
        question2.answer3 = "バラク・オバマ"

        question2.imageResource = R.drawable.john_adams

        val question3 = Question()
        question3.answer = 3

        question3.answer1 = "ジェームズ・モンロー"
        question3.answer2 = "アンドリュー・ジャクソン"
        question3.answer3 = "トーマス・ジェファーソン"

        question3.imageResource = R.drawable.thomas_jefferson

        val question4 = Question()
        question4.answer = 1

        question4.answer1 = "ジェームス・マディソン"
        question4.answer2 = "ビル・クリントン"
        question4.answer3 = "ジョン・タイラー"

        question4.imageResource = R.drawable.james_madison

        val question5 = Question()
        question5.answer = 3

        question5.answer1 = "トーマス・ジェファーソン"
        question5.answer2 = "アンドリュー・ジャクソン"
        question5.answer3 = "ジェームズ・モンロー"

        question5.imageResource = R.drawable.james_monroe

        val question6 = Question()
        question6.answer = 2

        question6.answer1 = "ウィリアム・タフト"
        question6.answer2 = "ジョン・クインシー・アダムス"
        question6.answer3 = "ハリー・トルーマン"

        question6.imageResource = R.drawable.jqa

        val question7 = Question()
        question7.answer = 3

        question7.answer1 = "ジョージ・ワシントン"
        question7.answer2 = "セオドア・ルーズベルト"
        question7.answer3 = "アンドリュー・ジャクソン"

        question7.imageResource = R.drawable.andrew_jackson

        val question8 = Question()
        question8.answer = 2

        question8.answer1 = "ロナルド・レーガン"
        question8.answer2 = "マーティン・ヴァン・ビューレン"
        question8.answer3 = "ジェームズ・ポーク"

        question8.imageResource = R.drawable.martin_van_buren

        val question9 = Question()
        question9.answer = 1

        question9.answer1 = "ウィリアム・ハリソン"
        question9.answer2 = "エイブラハム・リンカーン"
        question9.answer3 = "ウィリアム・タフト"

        question9.imageResource = R.drawable.william_henry_harrison

        val question10 = Question()
        question10.answer = 3

        question10.answer1 = "ドナルド・トランプ"
        question10.answer2 = "ビル・クリントン"
        question10.answer3 = "ジョン・タイラー"

        question10.imageResource = R.drawable.john_tyler

        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)
        questionList.add(question4)
        questionList.add(question5)
        questionList.add(question6)
        questionList.add(question7)
        questionList.add(question8)
        questionList.add(question9)
        questionList.add(question10)


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

        //setQuestionメソッドの呼び出し
        setQuestion()


            //選択肢が押されたら解答確認（answerCheckメソッド）
        buttonAnswer1.setOnClickListener { answerCheck(1) }
        buttonAnswer2.setOnClickListener { answerCheck(2) }
        buttonAnswer3.setOnClickListener { answerCheck(3) }

        //「NEXT」ボタンが押された時の処理
        buttonNext.setOnClickListener {
            if (numberOfRemaining == 10) {
                //終了のメッセージを表示
                textViewMessage.text = "FINISH!!"
                //TOPボタン以外を全て無効にする
                buttonAnswer1.isEnabled = false
                buttonAnswer2.isEnabled = false
                buttonAnswer3.isEnabled = false
                buttonNext.isEnabled = false
            } else {
                //次の問題を出題
                setQuestion()
            }
        }

        //「TOP」ボタンが押されたらMainActivityへ
        buttonTop.setOnClickListener { finish() }
    }

    //onResumeメソッドをオーバーライド
    override fun onResume() {
        super.onResume()

        //Timerクラスをインスタンス化
//        timer = Timer()
    }

    override fun onPause() {
        super.onPause()

        //Timerクラスをキャンセル
//        timer.cancel()
    }

    private fun setQuestion() {

        //「NEXT」ボタンを無効化
        buttonNext.isEnabled = false

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

        imageView.setImageResource(question.imageResource)
        answer = question.answer

        //1秒後に次の問題を出す（setQuestionメソッド）
//        timer.schedule(1000, {runOnUiThread {setQuestion()}} )

        //◯、×画像を見える様にする
        imageViewAnswer.visibility = View.INVISIBLE

        //問題数を表示
        numberOfRemaining ++
        textViewNumberOfQuestion.text = numberOfRemaining.toString()
    }

    private fun answerCheck(imageView: Int) {
        //「NEXT」ボタンを有効化
        buttonNext.isEnabled = true

        //正解なら◯、不正解なら×を表示する
        imageViewAnswer.visibility = View.VISIBLE
        if (imageView == answer) {
            imageViewAnswer.setImageResource(R.drawable.pic_correct)
        } else {
            imageViewAnswer.setImageResource(R.drawable.pic_incorrect)
        }
    }
}
