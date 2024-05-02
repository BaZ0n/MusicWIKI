package com.example.musicwiki

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.musicwiki.databinding.ActivitySongDetailsBinding

class SongDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = ContextCompat.getColor(this, R.color.botnavcolor)
        val db = MainDB.getDB(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySongDetailsBinding.inflate(layoutInflater)
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
                val song = item.audioLink
                val favorite = item.favorite
                binding.trackImage.setImageResource(imageId)
                binding.songNameTV.setText(title)
                binding.artistNameTV.setText(artistName)
                binding.songTextTV.setText(textTr)

                if (favorite == true) {
                    binding.favoriteBTN.setImageResource(R.drawable.nowfavorite)
                }
            }
        }

        binding.backBTN.setOnClickListener {
            finish()
        }

        binding.favoriteBTN.setOnClickListener {
            var isDataHandled = true // Флаг для отслеживания обработки данных
            db.getDao().idSearch(songID).asLiveData().observe(this) {list ->
                if (isDataHandled) {
                    for (item in list) {
                        val favorite = item.favorite
                        Thread {
                            db.getDao().favoriteUpdate(songID, !favorite)
                        }.start()

                        if (!favorite) {
                            binding.favoriteBTN.setImageResource(R.drawable.nowfavorite)
                        } else {
                            binding.favoriteBTN.setImageResource(R.drawable.addfavorite)
                        }
                        break
                    }
                    isDataHandled = false
                }

            }
        }

        binding.playBTN.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java).apply {
                // Передача информации о песне в Intent
                putExtra("id", intent.getIntExtra("id", 0))
                // Здесь можно добавить дополнительные данные о песне, если нужно
            }
            // Запуск активности с подробной информацией о песне
            this.startActivity(intent)
        }
    }
}
