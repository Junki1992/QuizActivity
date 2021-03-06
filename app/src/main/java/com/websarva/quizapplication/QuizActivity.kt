package com.websarva.quizapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*

//正解数をトップクラスで宣言（ResultActivityでも使用する為）
    var intCorrect = 0

class QuizActivity : AppCompatActivity() {

    var numberOfQuestion = 0
    val questionList = mutableListOf<Question>()
    //val imageViewList = mutableListOf<Int>()
    var answer = 0
    var soundId_Correct = 0
    var soundId_Incorrect = 0
    var answeredQuestions = 0

    lateinit var dialog: AlertDialog.Builder
    lateinit var soundPool: SoundPool
    lateinit var vibrator: Vibrator

    //play関数を拡張関数で宣言（play2）
    fun SoundPool.play2(soundId: Int) {
        this.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //画面が開いたら
        //MainActivityから渡された問題数をゲット
        val bundle = intent.extras
        numberOfQuestion = bundle!!.getInt("numberOfQuestion")

        //出題数を表示
        textViewQuestionNumber.text = numberOfQuestion.toString()

        //「NEXT」ボタンを無効化
        buttonNext.isEnabled = false

        //vibratorのインスタンスを取得
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        //出題パターンを定義
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
        question6.imageResource = R.drawable.john_quincy_adams

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

        val question11 = Question()
        question11.answer = 1
        question11.answer1 = "ジェームズ・ポーク"
        question11.answer2 = " チェスター・A・アーサー"
        question11.answer3= "フランクリン・ルーズベルト"
        question11.imageResource = R.drawable.james_polk

        val question12 = Question()
        question12.answer = 3
        question12.answer1 = "ジョン・クインシー・アダムズ"
        question12.answer2 = "ベンジャミン・ハリソン"
        question12.answer3 = "ザカリー・テイラー"
        question12.imageResource = R.drawable.zachary_taylor

        val question13 = Question()
        question13.answer = 1
        question13.answer1 = "ミラード・フィルモア"
        question13.answer2 = "ユリシーズ・グラント"
        question13.answer3 = "フランクリン・ルーズベルト"
        question13.imageResource = R.drawable.millard_fillmore

        val question14 = Question()
        question14.answer = 3
        question14.answer1 = "ジェームズ・ポーク"
        question14.answer2 = "ドナルド・トランプ"
        question14.answer3 = "フランクリン・ピアース"
        question14.imageResource = R.drawable.franklin_pierce

        val question15 = Question()
        question15.answer = 3
        question15.answer1 = "ユリシーズ・グラント"
        question15.answer2 = "ドナルド・トランプ"
        question15.answer3 = "ジェームズ・ブキャナン"
        question15.imageResource = R.drawable.james_buchanan

        val question16 = Question()
        question16.answer = 2
        question16.answer1 = "ドワイト・D・アイゼンハワー"
        question16.answer2 = "エイブラハム・リンカーン"
        question16.answer3 = "グロバー・クリーブランド"
        question16.imageResource = R.drawable.abraham_lincoln

        val question17 = Question()
        question17.answer = 1
        question17.answer1 = "アンドリュー・ジョンソン"
        question17.answer2 = "セオドア・ルーズベルト"
        question17.answer3 = "マーティン・ヴァン・ビューレン"
        question17.imageResource = R.drawable.andrew_johnson

        val question18 = Question()
        question18.answer = 3
        question18.answer1 = "ハリー・S・トルーマン"
        question18.answer2 = "ジョージ・ワシントン"
        question18.answer3 = "ユリシーズ・グラント"
        question18.imageResource = R.drawable.ulysses_grant

        val question19 = Question()
        question19.answer = 2
        question19.answer1 = "アンドリュー・ジョンソン"
        question19.answer2 = "ラザフォード・ヘイズ"
        question19.answer3 = "エイブラハム・リンカーン"
        question19.imageResource = R.drawable.rutherford_hayes

        val question20 = Question()
        question20.answer = 2
        question20.answer1 = "ザカリー・テイラー"
        question20.answer2 = "ジェームズ・ガーフィールド"
        question20.answer3 = "ジェームズ・ブキャナン"
        question20.imageResource = R.drawable.james_garfield

        val question21 = Question()
        question21.answer = 1
        question21.answer1 = "チェスター・A・アーサー"
        question21.answer2 = "ハーバート・フーヴァー"
        question21.answer3 = "リンドン・ジョンソン"
        question21.imageResource = R.drawable.chester_a_arthur

        val question22 = Question()
        question22.answer = 1
        question22.answer1 = "グロバー・クリーブランド"
        question22.answer2 = "ベンジャミン・ハリソン"
        question22.answer3 = "ロナルド・レーガン"
        question22.imageResource = R.drawable.grover_cleveland

        val question23 = Question()
        question23.answer = 3
        question23.answer1 = "カルビン・クーリッジ"
        question23.answer2 = "リチャード・ニクソン"
        question23.answer3 = "ベンジャミン・ハリソン"
        question23.imageResource = R.drawable.benjamin_harrison

        val question24 = Question()
        question24.answer = 2
        question24.answer1 = "ウッドロウ・ウィルソン"
        question24.answer2 = "ウィリアム・マッキンリー"
        question24.answer3 = "ウィリアム・タフト"
        question24.imageResource = R.drawable.william_mckinley

        val question25 = Question()
        question25.answer = 3
        question25.answer1 = "フランクリン・ルーズベルト"
        question25.answer2 = "ジミー・カーター"
        question25.answer3 = "セオドア・ルーズベルト"
        question25.imageResource = R.drawable.theodore_roosevelt

        val question26 = Question()
        question26.answer = 1
        question26.answer1 = "ウィリアム・タフト"
        question26.answer2 = "ウィリアム・マッキンリー"
        question26.answer3 = "ウィリアム・ハリソン"
        question26.imageResource = R.drawable.william_taft

        val question27 = Question()
        question27.answer = 2
        question27.answer1 = "ユリシーズ・グラント"
        question27.answer2 = "ウッドロウ・ウィルソン"
        question27.answer3 = "ハーバート・フーバー"
        question27.imageResource = R.drawable.woodrow_wilson

        val question28 = Question()
        question28.answer = 1
        question28.answer1 = "ウォレン・ハーディング"
        question28.answer2 = "ロナルド・レーガン"
        question28.answer3 = "カルビン・クーリッジ"
        question28.imageResource = R.drawable.warren_g_harding

        val question29 = Question()
        question29.answer = 1
        question29.answer1 = "カルビン・クーリッジ"
        question29.answer2 = "ハリー・S・トルーマン"
        question29.answer3 = "セオドア・ルーズベルト"
        question29.imageResource = R.drawable.calvin_coolidge

        val question30 = Question()
        question30.answer = 2
        question30.answer1 = "ドワイト・D・アイゼンハワー"
        question30.answer2 = "ハーバート・フーヴァー"
        question30.answer3 = "ベンジャミン・ハリソン"
        question30.imageResource = R.drawable.herbert_hoover

        val question31 = Question()
        question31.answer = 3
        question31.answer1 = "リチャード・ニクソン"
        question31.answer2 = "リンドン・ジョンソン"
        question31.answer3 = "フランクリン・ルーズベルト"
        question31.imageResource = R.drawable.franklin_delano_roosevelt

        val question32 = Question()
        question32.answer = 1
        question32.answer1 = "ハリー・S・トルーマン"
        question32.answer2 = "チェスター・A・アーサー"
        question32.answer3 = "クロバー・クリーブランド"
        question32.imageResource = R.drawable.hurry_truman

        val question33 = Question()
        question33.answer = 1
        question33.answer1 = "ドワイト・D・アイゼンハワー"
        question33.answer2 = "ジェラルド・R・フォード"
        question33.answer3 = "ジミー・カーター"
        question33.imageResource = R.drawable.dwight_d_eisenhower

        val question34 = Question()
        question34.answer = 1
        question34.answer1 = "ジョン・F・ケネディ"
        question34.answer2 = "カルビン・クーリッジ"
        question34.answer3 = "ジェームズ・ブキャナン"
        question34.imageResource = R.drawable.john_f_kennedy

        val question35 = Question()
        question35.answer = 2
        question35.answer1 = "ウッドロウ・ウィルソン"
        question35.answer2 = "リンドン・ジョンソン"
        question35.answer3 = "ウォレン・ハーディング"
        question35.imageResource = R.drawable.lyndon_johnson

        val question36 = Question()
        question36.answer = 3
        question36.answer1 = "ロナルド・レーガン"
        question36.answer2 = "ジミー・カーター"
        question36.answer3 = "リチャード・ニクソン"
        question36.imageResource = R.drawable.richard_nixon

        val question37 = Question()
        question37.answer = 2
        question37.answer1 = "ハリー・S・トルーマン"
        question37.answer2 = "ジェラルド・R・フォード"
        question37.answer3 = "ジョン・F・ケネディ"
        question37.imageResource = R.drawable.gerald_ford

        val question38 = Question()
        question38.answer = 3
        question38.answer1 = "フランクリン・ルーズベルト"
        question38.answer2 = "ウィリアム・マッキンリー"
        question38.answer3 = "ジミー・カーター"
        question38.imageResource = R.drawable.james_carter

        val question39 = Question()
        question39.answer = 1
        question39.answer1 = "ロナルド・レーガン"
        question39.answer2 = "ジェームズ・ポーク"
        question39.answer3 = "ジョン・タイラー"
        question39.imageResource = R.drawable.ronald_reagan

        val question40 = Question()
        question40.answer = 3
        question40.answer1 = "ジョージ・W・ブッシュ"
        question40.answer2 = "リンドン・ジョンソン"
        question40.answer3 = "ジョージ・H・W・ブッシュ"
        question40.imageResource = R.drawable.george_h_w_bush

        val question41 = Question()
        question41.answer = 1
        question41.answer1 = "ビル・クリントン"
        question41.answer2 = "ジェームズ・ガーフィールド"
        question41.answer3 = "ウィリアム・タフト"
        question41.imageResource = R.drawable.bill_clinton

        val question42 = Question()
        question42.answer = 2
        question42.answer1 = "ジョージ・H・W・ブッシュ"
        question42.answer2 = "ジョージ・W・ブッシュ"
        question42.answer3 = "カルビン・クーリッジ"
        question42.imageResource = R.drawable.george_w_bush

        val question43 = Question()
        question43.answer = 2
        question43.answer1 = "ジェームズ・モンロー"
        question43.answer2 = "バラク・オバマ"
        question43.answer3 = "ベンジャミン・ハリソン"
        question43.imageResource = R.drawable.barack_obama

        val question44 = Question()
        question44.answer = 1
        question44.answer1 = "ドナルド・トランプ"
        question44.answer2 = "ジョージ・ワシントン"
        question44.answer3 = "ユリシーズ・グラント"
        question44.imageResource = R.drawable.donald_trump

        val question45 = Question()
        question45.answer = 3
        question45.answer1 = "ミラード・フィルモア"
        question45.answer2 = "ラザフォード・ヘイズ"
        question45.answer3 = "グロバー・クリーブランド"
        question45.imageResource = R.drawable.grover_cleveland


    //questionListに登録
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
        questionList.add(question11)
        questionList.add(question12)
        questionList.add(question13)
        questionList.add(question14)
        questionList.add(question15)
        questionList.add(question16)
        questionList.add(question17)
        questionList.add(question18)
        questionList.add(question19)
        questionList.add(question20)
        questionList.add(question21)
        questionList.add(question22)
        questionList.add(question23)
        questionList.add(question24)
        questionList.add(question25)
        questionList.add(question26)
        questionList.add(question27)
        questionList.add(question28)
        questionList.add(question29)
        questionList.add(question30)
        questionList.add(question31)
        questionList.add(question32)
        questionList.add(question33)
        questionList.add(question34)
        questionList.add(question35)
        questionList.add(question36)
        questionList.add(question37)
        questionList.add(question38)
        questionList.add(question39)
        questionList.add(question40)
        questionList.add(question41)
        questionList.add(question42)
        questionList.add(question43)
        questionList.add(question44)
        questionList.add(question45)

        //問題をシャッフル
        questionList.shuffle()

        //setQuestionメソッドの呼び出し
        setQuestion()


        //選択肢が押されたら解答確認（answerCheckメソッド）
        buttonAnswer1.setOnClickListener { answerCheck(1) }
        buttonAnswer2.setOnClickListener { answerCheck(2) }
        buttonAnswer3.setOnClickListener { answerCheck(3) }

        //「NEXT」ボタンが押された時の処理
        buttonNext.setOnClickListener {
            if (numberOfQuestion == answeredQuestions) {
                //終了のメッセージを表示
                textViewMessage.text = "FINISH!!"
                //選択肢を全て無効にする
                buttonAnswer1.isEnabled = false
                buttonAnswer2.isEnabled = false
                buttonAnswer3.isEnabled = false
                //テキストをNEXTからRESULTに変更
                buttonNext.text = "RESULT"
                //メッセージを表示
                buttonAnswer3.text = "「RESULT」を長押し"
                buttonAnswer3.setTextColor(Color.BLACK)
                buttonNext.setOnLongClickListener {
                    val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                    intent.putExtra("numberOfQuestion", numberOfQuestion)
                    intent.putExtra("intCorrect", intCorrect)
                    startActivity(intent)
                    true
                }
            } else {
                //次の問題を出題
                setQuestion()
            }
        }

        //「TOP」ボタンが押された際の処理
        buttonTop.setOnClickListener {
            //クイズがまだ途中の場合 → ダイアログを表示して確認
            if (answeredQuestions < numberOfQuestion) {
                dialog = AlertDialog.Builder(this@QuizActivity).apply {
                    setTitle("クイズの終了")
                    setMessage("クイズを終了してタイトル画面に戻りますか？")
                    setPositiveButton("はい") { dialog, which ->
                        finish()
                    }
                    setNegativeButton("いいえ") { dialog, which ->  }
                    show()
                }
              //全問終了の場合 → ダイアログで確認
            } else if (answeredQuestions == numberOfQuestion) {
                dialog = AlertDialog.Builder(this@QuizActivity).apply {
                    setTitle("クイズの終了")
                    setMessage("結果を確認せずにタイトル画面に戻りますか？")
                    setPositiveButton("はい") { dialog, which ->
                        finish()
                    }
                    setNegativeButton("いいえ") { dialog, which -> }
                    show()
                }
            }
        }
    }

    //onResumeメソッドをオーバーライド
    override fun onResume() {
        super.onResume()
        //正解数を0にする
        intCorrect = 0

        //SoundPoolクラスをインスタンス化
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder().setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build())
                .setMaxStreams(1)
                .build()
        } else {
            SoundPool (1, AudioManager.STREAM_MUSIC, 0)
        }

        //音声ファイルをメモリにロード
        soundId_Correct = soundPool.load(this, R.raw.sound_correct, 1)
        soundId_Incorrect = soundPool.load(this, R.raw.sound_incorrect, 1)
    }

    override fun onPause() {
        super.onPause()
        //使用済みの音声ファイルをメモリから後片付け
        soundPool.release()
        //vibratorをオフ
        vibrator.cancel()
    }

    private fun setQuestion() {
        //「NEXT」ボタンを無効化
        buttonNext.isEnabled = false

        //buttonAnswer1,2,3を有効化
        buttonAnswer1.isEnabled = true
        buttonAnswer2.isEnabled = true
        buttonAnswer3.isEnabled = true

        //選択肢の乱数を生成
        // val randomQuestion = Random()
        //val questionIndex = randomQuestion.nextInt(questionList.count())
        // Log.d("questionIndex", questionIndex.toString())

        imageView.setImageResource(R.drawable.george_washington)

        val question = questionList[answeredQuestions]

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

        //回答数を表示
        answeredQuestions ++
        textViewAnsweredQuestion.text = answeredQuestions.toString()
    }

    private fun answerCheck(imageView: Int) {
        //「NEXT」ボタンを有効化
        buttonNext.isEnabled = true

        //buttonAnswer1,2,3を有効化
        buttonAnswer1.isEnabled = false
        buttonAnswer2.isEnabled = false
        buttonAnswer3.isEnabled = false

        //正解なら◯、不正解なら×を表示し、音声ファイルを再生する
        imageViewAnswer.visibility = View.VISIBLE
        if (imageView == answer) {
            imageViewAnswer.setImageResource(R.drawable.correct)
            //正解なら正解数をカウント
            intCorrect ++
            textViewCorrect.text = intCorrect.toString()
            soundPool.play2(soundId_Correct)
        } else {
            imageViewAnswer.setImageResource(R.drawable.incorrect)
            soundPool.play2(soundId_Incorrect)
            //端末を振動させる
            vibrator.vibrate(50L)
        }
    }
}