package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mistpaag.lastfm.trainee.R
import org.koin.android.ext.android.inject

class TopArtistFragment : Fragment() {

    companion object {
        fun newInstance() =
            TopArtistFragment()
    }

    private val viewModel by inject<TopArtistViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_artist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}