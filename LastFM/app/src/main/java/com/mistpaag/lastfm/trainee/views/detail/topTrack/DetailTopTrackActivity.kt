package com.mistpaag.lastfm.trainee.views.detail.topTrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import coil.api.load
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.ActivityDetailTopTrackBinding
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.views.detail.DetailWebActivity
import org.koin.android.ext.android.inject

class DetailTopTrackActivity : AppCompatActivity() {

    private val viewModel by inject<DetailTopTrackViewModel> ()
    private lateinit var binding: ActivityDetailTopTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_top_track)

        val args = intent.extras
        var name = "" // or other values

        if (args != null) {
            name = args.getString("name").toString()
            viewModel.fetchTopTrack(name)
        }
        binding.backImage.setOnClickListener {
            super.onBackPressed()
        }



        viewModel.topTrack.observe(this, Observer {topTrack->
            with(binding){
                tittleDetail.text = topTrack.name
                trackImage.load(topTrack.image)
                timeText.text = "${resources.getString(R.string.time)} : ${topTrack.duration}"
                listenersText.text = "${resources.getString(R.string.listeners)} :${topTrack.listeners}"
                artistText.text = "${resources.getString(R.string.artist)} :${topTrack.artistTrack.name}"
                urlTrack.text = Html.fromHtml("<u>${topTrack.url}</u>")
                urlArtist.text = Html.fromHtml("<u>${topTrack.artistTrack.url}</u>")
            }
            binding.urlTrack.setOnClickListener {
                goToDetailWeb(topTrack.name, topTrack.url)
            }
            binding.urlArtist.setOnClickListener {
                goToDetailWeb(topTrack.artistTrack.name, topTrack.artistTrack.url)
            }

        })
    }

    fun goToDetailWeb(tittle:String, url:String){
        val intent = Intent(this, DetailWebActivity::class.java)
        intent.putExtra("tittle",tittle)
        intent.putExtra("url",url)
        startActivity(intent)
    }
}