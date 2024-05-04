package com.example.musicwiki

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class catalog_fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_catalog, container, false)
        val db = MainDB.getDB(requireContext())
        var isDataHandledSong = true // Флаг для отслеживания обработки данных
        var isDataHandledArtist = true

        db.getDao().getAllItemArtist().asLiveData().observe(viewLifecycleOwner){ list ->
            if (isDataHandledArtist) {
                Toast.makeText(requireContext(), "Artist", Toast.LENGTH_SHORT).show()
                for (item in list) {
                    val idArtist = item.id?.toInt()
                    val artistName = item.artistName
                    val history = item.history
                    val imageId = item.imageID
                    if (idArtist != null) {
                        createArtistView(rootView, requireContext(), imageId, artistName, idArtist)
                    }
                }
                isDataHandledArtist = false
            }
        }


        db.getDao().getAllItem().asLiveData().observe(viewLifecycleOwner){ list ->
            if (isDataHandledSong) {
                Toast.makeText(requireContext(), "Song", Toast.LENGTH_SHORT).show()
                for (item in list) {
                    val idSong = item.id?.toInt()
                    val title = item.trackName
                    val artistName = item.artistName
                    val imageId = item.imageLink
                    if (idSong != null) {
                        createSongView(rootView, requireContext(), imageId, title, artistName, idSong)
                    }
                }
                isDataHandledSong = false
            }
        }

        return rootView
    }

    fun createSongView(rootView: View, context: Context, imageResId: Int, songName: String, artistName: String, id: Int) {
        val density = context.resources.displayMetrics.density
        val colorWhite = ContextCompat.getColor(context, R.color.white)
        val pxWidth = (70 * density).toInt()
        val pxHeight = (70 * density).toInt()

        val newLinearLayoutSong = LinearLayout(context)
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
            val intent = Intent(context, SongDetailsActivity::class.java).apply {
                // Передача информации о песне в Intent
                putExtra("id", id)
                // Здесь можно добавить дополнительные данные о песне, если нужно
            }
            // Запуск активности с подробной информацией о песне
            context.startActivity(intent)
        }
        newLinearLayoutSong.setOnClickListener(onClickListener) // Устанавливаем обработчик нажатия

        val imageView = ShapeableImageView(context)
        val imageLayoutParams = LinearLayout.LayoutParams(
            pxWidth,
            pxHeight
        )
        imageView.layoutParams = imageLayoutParams
        imageView.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, 50f)
            .build()
        imageView.setImageResource(imageResId)

        val verticalLinearLayout = LinearLayout(context)
        verticalLinearLayout.orientation = LinearLayout.VERTICAL
        val verticalLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        verticalLinearLayout.layoutParams = verticalLayoutParams

        val songTextView = TextView(context)
        val songTextLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        songTextView.layoutParams = songTextLayoutParams
        songTextView.text = songName
        songTextView.textSize = 18f
        songTextView.setTextColor(colorWhite)
        songTextView.typeface = ResourcesCompat.getFont(context, R.font.rubik_medium) // Устанавливаем шрифт
        songTextView.setPadding(20, 10, 0, 0) // Добавляем отступ слева для выравнивания текста

        val artistTextView = TextView(context)
        val artistTextLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        artistTextView.layoutParams = artistTextLayoutParams
        artistTextView.text = artistName
        artistTextView.textSize = 18f
        artistTextView.setTextColor(ContextCompat.getColor(context, R.color.textGray)) // Серый цвет текста
        artistTextView.typeface = ResourcesCompat.getFont(context, R.font.rubik_regular) // Устанавливаем шрифт
        artistTextView.setPadding(20, 0, 0, 0) // Добавляем отступ слева для выравнивания текста

        newLinearLayoutSong.addView(imageView)
        verticalLinearLayout.addView(songTextView)
        verticalLinearLayout.addView(artistTextView)
        newLinearLayoutSong.addView(verticalLinearLayout)

        val songContainer = rootView.findViewById<LinearLayout>(R.id.trackCont)
        songContainer.addView(newLinearLayoutSong)
    }

    fun createArtistView(rootView: View, context: Context, imageResId: Int, artistName: String, id: Int) {
        val density = context.resources.displayMetrics.density
        val colorWhite = ContextCompat.getColor(context, R.color.white)
        val pxWidth = (85 * density).toInt()
        val pxHeight = (85 * density).toInt()

        val newLinearLayoutArtist = LinearLayout(context)
        newLinearLayoutArtist.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 5, 0)
        newLinearLayoutArtist.layoutParams = layoutParams

        val imageView = ShapeableImageView(context)
        val imageLayoutParams = LinearLayout.LayoutParams(
            pxWidth,
            pxHeight
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = imageLayoutParams
        imageView.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, 50f)
            .build()
        imageView.setImageResource(imageResId)

        val textView = TextView(context)
        val textLayoutParams = LinearLayout.LayoutParams(
            pxWidth,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textLayoutParams
        textView.gravity = Gravity.CENTER
        textView.text = artistName
        textView.textSize = 16f
        textView.typeface = ResourcesCompat.getFont(context, R.font.rubik_medium) // Устанавливаем шрифт
        textView.setTextColor(colorWhite)

        newLinearLayoutArtist.addView(imageView)
        newLinearLayoutArtist.addView(textView)

        val artistCont = rootView.findViewById<LinearLayout>(R.id.artistCont)
        artistCont.addView(newLinearLayoutArtist)
    }
}
