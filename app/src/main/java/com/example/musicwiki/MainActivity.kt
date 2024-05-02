package com.example.musicwiki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = ContextCompat.getColor(this, R.color.botnavcolor)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_catalog -> {
                    replaceFragment(catalog_fragment())
                    true
                }
                R.id.navigation_favorite -> {
                    replaceFragment(favorite_fragment())
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(profile_fragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(catalog_fragment())
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}