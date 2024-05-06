package com.example.musicwiki

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.musicwiki.databinding.ActivityArtistBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class artistActivity : AppCompatActivity() {

    lateinit var binding: ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = ContextCompat.getColor(this, R.color.botnavcolor)
        super.onCreate(savedInstanceState)
        val db = MainDB.getDB(this)
        enableEdgeToEdge()
        binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val artistID = intent.getIntExtra("id", 0)
        val artistName = intent.getStringExtra("artistName")
        db.getDaoArtist().idSearch(artistID).asLiveData().observe(this) {list->
            for (item in list) {
                var imageID = item.imageID
                var history = item.history

                binding.artistNameTV.setText(artistName)
                binding.artistImage.setImageResource(imageID)
                binding.historyArtist.setText(history)
            }
        }

        db.getDao().songsFromArtist(artistName).asLiveData().observe(this) {list->
            for (item in list) {
                val idSong = item.id
                val title = item.trackName
                val artistName = item.artistName
                val imageId = item.imageLink

                if (idSong != null) {
                    createSongView(imageId, title, artistName, idSong)
                }
            }
        }

        binding.backBTN.setOnClickListener {
            finish()
        }
    }


    fun createSongView(imageResId: Int, songName: String, artistName: String, id: Int) {
        val density = this.resources.displayMetrics.density
        val colorWhite = ContextCompat.getColor(this, R.color.white)
        val pxWidth = (70 * density).toInt()
        val pxHeight = (70 * density).toInt()

        val newLinearLayoutSong = LinearLayout(this)
        newLinearLayoutSong.orientation = LinearLayout.HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(40, 20, 0, 0) // Добавляем отступ снизу для разделения элементов списка
        newLinearLayoutSong.layoutParams = layoutParams
        newLinearLayoutSong.isClickable = true

        val onClickListener = View.OnClickListener {
            // Создание Intent для запуска SongDetailsActivity
            val intent = Intent(this, SongDetailsActivity::class.java).apply {
                // Передача информации о песне в Intent
                putExtra("id", id)
                // Здесь можно добавить дополнительные данные о песне, если нужно
            }
            // Запуск активности с подробной информацией о песне
            this.startActivity(intent)
        }
        newLinearLayoutSong.setOnClickListener(onClickListener) // Устанавливаем обработчик нажатия

        val imageView = ShapeableImageView(this)
        val imageLayoutParams = LinearLayout.LayoutParams(
            pxWidth,
            pxHeight
        )
        imageView.layoutParams = imageLayoutParams
        imageView.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, 50f)
            .build()
        imageView.setImageResource(imageResId)

        val verticalLinearLayout = LinearLayout(this)
        verticalLinearLayout.orientation = LinearLayout.VERTICAL
        val verticalLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        verticalLinearLayout.layoutParams = verticalLayoutParams

        val songTextView = TextView(this)
        val songTextLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        songTextView.layoutParams = songTextLayoutParams
        songTextView.text = songName
        songTextView.textSize = 18f
        songTextView.setTextColor(colorWhite)
        songTextView.typeface = ResourcesCompat.getFont(this, R.font.rubik_medium) // Устанавливаем шрифт
        songTextView.setPadding(20, 10, 0, 0) // Добавляем отступ слева для выравнивания текста

        val artistTextView = TextView(this)
        val artistTextLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        artistTextView.layoutParams = artistTextLayoutParams
        artistTextView.text = artistName
        artistTextView.textSize = 18f
        artistTextView.setTextColor(ContextCompat.getColor(this, R.color.textGray)) // Серый цвет текста
        artistTextView.typeface = ResourcesCompat.getFont(this, R.font.rubik_regular) // Устанавливаем шрифт
        artistTextView.setPadding(20, 0, 0, 0) // Добавляем отступ слева для выравнивания текста

        newLinearLayoutSong.addView(imageView)
        verticalLinearLayout.addView(songTextView)
        verticalLinearLayout.addView(artistTextView)
        newLinearLayoutSong.addView(verticalLinearLayout)

        val songContainer = findViewById<LinearLayout>(R.id.artistSongCont)
        songContainer.addView(newLinearLayoutSong)
    }
}