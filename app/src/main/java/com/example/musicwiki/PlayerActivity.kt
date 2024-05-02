package com.example.musicwiki

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.musicwiki.databinding.ActivityPlayerBinding
import java.util.Locale
import java.util.concurrent.TimeUnit


class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var audioManager: AudioManager? = null
    private lateinit var audioFocusChangeListener: AudioManager.OnAudioFocusChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            startForegroundService(Intent(this, MediaPlayerService::class.java).also {
                it.putExtra("EXTRA_TYPE", "music")
            })
        } else {
            startService(Intent(this, MediaPlayerService::class.java))
        }*/

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
                val title = item.trackName
                val artistName = item.artistName
                val imageId = item.imageLink
                val textTr = item.textTr
                val songID = item.audioLink
                val duration = item.durationTr

                binding.trackImagePlay.setImageResource(imageId)
                binding.artistNamePlay.setText(artistName)
                binding.songNameTVPlay.setText(title)
                binding.nowPlayingTV.setText(title)
                binding.durationTVPlay.setText(duration)


                mediaPlayer = MediaPlayer.create(this, songID)
                binding.seekBarPlay.max = mediaPlayer.duration
            }
        }

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                // Установка текущего положения SeekBar в соответствии с текущим временем аудиоплеера
                binding.seekBarPlay.progress = mediaPlayer.currentPosition

                val currentPositionMs = mediaPlayer.currentPosition
                val minutes = TimeUnit.MILLISECONDS.toMinutes(currentPositionMs.toLong())
                val seconds = TimeUnit.MILLISECONDS.toSeconds(currentPositionMs.toLong()) -
                        TimeUnit.MINUTES.toSeconds(minutes)

                val timeText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                binding.nowTVPlay.setText(timeText)
                // Повторное запуск этого Runnable через 1 секунду
                handler.postDelayed(this, 1000)
            }
        }

        binding.seekBarPlay.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Если пользователь переместил ползунок, обновите позицию аудиоплеера
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
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
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                handler.removeCallbacks(runnable)
                binding.playPauseBTN.setImageResource(R.drawable.playicon)
            }
            else {
                mediaPlayer.start()
                handler.post(runnable)
                binding.playPauseBTN.setImageResource(R.drawable.pauseicon)
            }
        }

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        audioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    // Восстановить воспроизведение
                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.setVolume(1f, 1f)
                        mediaPlayer.start()
                    }
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    // Перестать воспроизведение и освободить ресурсы
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    // Временно остановить воспроизведение
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    }
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.setVolume(0.5f, 0.5f)
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
            mediaPlayer.start()
        }

        binding.backBTN.setOnClickListener {
            mediaPlayer.release()
            finish()
        }

        binding.repeatBTN.setOnClickListener {
            if (mediaPlayer.isLooping() == false) {
                mediaPlayer.setLooping(true)
                binding.repeatBTN.setImageResource(R.drawable.loopicon)
            }
            else {
                mediaPlayer.setLooping(false)
                binding.repeatBTN.setImageResource(R.drawable.singleicon)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Освобождаем ресурсы MediaPlayer при завершении активности
        mediaPlayer.release()
    }
}
