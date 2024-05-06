package com.example.musicwiki

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.musicwiki.databinding.ActivityPlayerBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Locale
import java.util.concurrent.TimeUnit


class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    private var mediaPlayer: MediaPlayer? = null
    private var audioManager: AudioManager? = null
    private lateinit var audioFocusChangeListener: AudioManager.OnAudioFocusChangeListener
    private var handler = Handler(Looper.getMainLooper())
    private var textTr: String? = ""
    private var artistName: String? = ""
    private var title: String? = ""
    private var track: Int? = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        mediaPlayer = MediaPlayer()
        val db = MainDB.getDB(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val songID = intent.getIntExtra("id", 0)
        db.getDao().idSearch(songID).asLiveData().observe(this){ list ->
            for (item in list) {
                title = item.trackName
                artistName = item.artistName
                val imageId = item.imageLink
                textTr = item.textTr
                track = item.audioLink
                val duration = item.durationTr

                binding.trackImagePlay.setImageResource(imageId)
                binding.artistNamePlay.setText(artistName)
                binding.songNameTVPlay.setText(title)
                binding.nowPlayingTV.setText(title)
                binding.durationTVPlay.setText(duration)


                mediaPlayer = MediaPlayer.create(this, track!!)
                binding.seekBarPlay.max = mediaPlayer!!.duration
            }
        }

        binding.seekBarPlay.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Если пользователь переместил ползунок, обновите позицию аудиоплеера
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
               // TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })


        binding.playPauseBTN.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer?.pause()
                handler.removeCallbacks(runnable)
                binding.playPauseBTN.setImageResource(R.drawable.playicon)
            }
            else {
                mediaPlayer?.start()
                handler.post(runnable)
                binding.playPauseBTN.setImageResource(R.drawable.pauseicon)
            }
        }

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        audioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    // Восстановить воспроизведение
                    if (!mediaPlayer!!.isPlaying) {
                        mediaPlayer?.setVolume(1f, 1f)
                        mediaPlayer?.start()
                    }
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    // Перестать воспроизведение и освободить ресурсы
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    // Временно остановить воспроизведение
                    if (mediaPlayer!!.isPlaying) {
                        mediaPlayer?.pause()
                    }
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    if (mediaPlayer!!.isPlaying) {
                        mediaPlayer?.setVolume(0.5f, 0.5f)
                    }
                }
            }
        }

        val result = audioManager!!.requestAudioFocus(
            audioFocusChangeListener,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN
        )

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Получить аудиофокус и начать воспроизведение
            mediaPlayer?.start()
        }

        binding.backBTN.setOnClickListener {
            handler.removeCallbacks(runnable)
            finish()
        }

        binding.repeatBTN.setOnClickListener {
            if (mediaPlayer?.isLooping() == false) {
                mediaPlayer?.setLooping(true)
                binding.repeatBTN.setImageResource(R.drawable.loopicon)
            }
            else {
                mediaPlayer?.setLooping(false)
                binding.repeatBTN.setImageResource(R.drawable.singleicon)
            }
        }

        binding.previousBTN.setOnClickListener {
            mediaPlayer?.seekTo(0)
            mediaPlayer?.start()
        }

        val seekDurationMs = 5000 // Длительность удержания кнопки в миллисекундах
        binding.nextBTN.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    mediaPlayer?.pause()
                    mediaPlayer?.seekTo((mediaPlayer?.currentPosition ?: 0) + seekDurationMs)
                }
                MotionEvent.ACTION_UP -> {
                    view.removeCallbacks(null)
                    mediaPlayer?.start()
                }
            }
            true
        }

        binding.textBTN.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_text, null)
            val textTV = view.findViewById<TextView>(R.id.textTextView)
            val artistNameTV = view.findViewById<TextView>(R.id.artistNameTVBottom)
            val songNameTV = view.findViewById<TextView>(R.id.songNameTVBottom)

            textTV.setText(textTr)
            artistNameTV.setText(artistName)
            songNameTV.setText(title)

            dialog.setContentView(view)
            dialog.show()
        }
    }

    private val runnable = object : Runnable {
        override fun run() {
            if (mediaPlayer != null && mediaPlayer!!.isPlaying && mediaPlayer!!.duration > 0) {
                // Установка текущего положения SeekBar в соответствии с текущим временем аудиоплеера
                binding.seekBarPlay.progress = mediaPlayer!!.currentPosition

                val currentPositionMs = mediaPlayer!!.currentPosition
                val minutes = TimeUnit.MILLISECONDS.toMinutes(currentPositionMs.toLong())
                val seconds = TimeUnit.MILLISECONDS.toSeconds(currentPositionMs.toLong()) -
                        TimeUnit.MINUTES.toSeconds(minutes)

                val timeText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                binding.nowTVPlay.setText(timeText)
            }
            // Повторное запуск этого Runnable через 1 секунду
            handler.postDelayed(this, 1000)
        }
    }

    override fun onPause() {
        super.onPause()
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer?.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
//        mediaPlayer?.release()
    }
}
