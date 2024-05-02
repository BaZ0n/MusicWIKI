package com.example.musicwiki

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.musicwiki.databinding.FragmentFavoriteBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class favorite_fragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context: Context = requireActivity()
        val db = MainDB.getDB(context)
        //val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        var isDataHandler = true
        var trackNum: Int = 1
        db.getDao().getAllItem().asLiveData().observe(viewLifecycleOwner) {list ->
            if (isDataHandler) {
                for (item in list) {
                    val id = item.id
                    val trackName = item.trackName
                    val artistName = item.artistName
                    val imageId = item.imageLink
                    val favorite = item.favorite

                    if (favorite == true) {
                        if (id != null) {
                            createSongView(binding.root, requireContext(), imageId, trackName, artistName, id, trackNum)
                            trackNum++
                        }
                    }
                }
                trackNum = 1
            }
            isDataHandler = false
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = MainDB.getDB(requireActivity())
        super.onViewCreated(view, savedInstanceState)
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                binding.parentContainerFavorite.removeAllViews()
                var isDataHandler = true
                var trackNum: Int = 1
                db.getDao().getAllWhere(newText).asLiveData().observe(viewLifecycleOwner) {list ->
                    if (isDataHandler) {
                        for (item in list) {
                            val id = item.id
                            val trackName = item.trackName
                            val artistName = item.artistName
                            val imageId = item.imageLink
                            val favorite = item.favorite

                            if (favorite == true) {
                                if (id != null) {
                                    createSongView(binding.root, requireContext(), imageId, trackName, artistName, id, trackNum)
                                    trackNum++
                                }
                            }
                        }
                    }
                    trackNum = 1
                    isDataHandler = false
                }
                return true
            }
        })
    }



    fun createSongView(rootView: View, context: Context, imageResId: Int, songName: String, artistName: String, id: Int, trackNum: Int) {
        val density = context.resources.displayMetrics.density
        val colorWhite = ContextCompat.getColor(context, R.color.white)
        val pxWidth = (50 * density).toInt()
        val pxHeight = (50 * density).toInt()

        val newLinearLayout = LinearLayout(context)
        newLinearLayout.orientation = LinearLayout.HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(40, 20, 0, 15) // Добавляем отступ снизу для разделения элементов списка
        newLinearLayout.layoutParams = layoutParams
        newLinearLayout.isClickable = true

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
        newLinearLayout.setOnClickListener(onClickListener) // Устанавливаем обработчик нажатия

        val trackNumTV = TextView(context)
        val trackNumLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        trackNumTV.layoutParams = trackNumLayoutParams
        trackNumTV.text = trackNum.toString()
        trackNumTV.textSize = 18f
        trackNumTV.setTextColor(colorWhite)
        trackNumTV.typeface = ResourcesCompat.getFont(context, R.font.rubik_medium)
        trackNumTV.setPadding(0, 40, 30, 0)

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
        artistTextView.textSize = 16f
        artistTextView.setTextColor(ContextCompat.getColor(context, R.color.textGray)) // Серый цвет текста
        artistTextView.typeface = ResourcesCompat.getFont(context, R.font.rubik_regular) // Устанавливаем шрифт
        artistTextView.setPadding(20, 0, 0, 0) // Добавляем отступ слева для выравнивания текста


        val separator = View(context)
        separator.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1) // Ширина и высота разделителя
        separator.setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray)) // Цвет разделителя

        newLinearLayout.addView(trackNumTV)
        newLinearLayout.addView(imageView)
        verticalLinearLayout.addView(songTextView)
        verticalLinearLayout.addView(artistTextView)
        newLinearLayout.addView(verticalLinearLayout)

        binding.parentContainerFavorite.addView(newLinearLayout)
        binding.parentContainerFavorite.addView(separator)
    }
}