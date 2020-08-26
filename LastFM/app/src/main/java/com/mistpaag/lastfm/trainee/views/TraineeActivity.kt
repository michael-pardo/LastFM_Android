package com.mistpaag.lastfm.trainee.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.ActivityTraineeBinding

class TraineeActivity : AppCompatActivity() {

    lateinit var binding: ActivityTraineeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trainee)
    }
}