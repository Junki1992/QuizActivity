package com.websarva.quizapplication

import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var numberOfQuestion = 0
    var soundID1 = 0
    var soundID2 = 0

    lateinit var soundPool: SoundPool

    //play関数を拡張関数で宣言（play2）
    fun SoundPool.play2(soundId: Int) {
        this.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

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

        //クリックリスナー
        buttonNextResult.setOnClickListener {
            //onResultの呼び出し
            onResult()
        }
        //TRY AGAINボタンでMainActivityへ遷移
        buttonTryAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
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
        //音声ファイルをメモリーにロード
        soundID1 = soundPool.load(this, R.raw.clear, 1)
        soundID2 = soundPool.load(this, R.raw.failure, 1)
    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    private fun onResult() {
        //結果を判定（合格なら青字で”CLEAR!!”、不合格なら赤字で”FAILURE...”を表示）
        //判定結果に合わせて音声ファイルを再生
        if (intCorrect >= numberOfQuestion -5) {
            textViewResultMessage.text = "CLEAR!!"
            textViewResultMessage.setTextColor(Color.BLUE)
            soundPool.play2(soundID1)
        } else {
            textViewResultMessage.text = "FAILURE..."
            textViewResultMessage.setTextColor(Color.RED)
            soundPool.play2(soundID2)
        }
        //NEXTボタンを無効化
        buttonNextResult.isEnabled = false
    }
}