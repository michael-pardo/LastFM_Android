package com.mistpaag.lastfm.trainee.views.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.ActivityDetailWebBinding
import com.mistpaag.lastfm.trainee.utils.haveConnection

class DetailWebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_web)

        val args = intent.extras
        var url = "Web View" // or other values
        var tittle = "1" // or other values

        if (args != null) {
            url = args.getString("url").toString()
            tittle = args.getString("tittle").toString()
        }

        binding.tittleWeb.text = tittle
        if (baseContext.haveConnection()){
            binding.detailWebview.loadUrl(url)
        }

        binding.backImage.setOnClickListener {
            super.onBackPressed()
        }

    }


}