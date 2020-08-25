package com.mistpaag.lastfm.trainee.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host)
        binding.bottomNav.setupWithNavController(navController)


    }

    override fun onBackPressed() {
        this.moveTaskToBack(true)
    }
}