package com.mistpaag.lastfm.trainee.views.main.topTrack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.TopTrackFragmentBinding
import org.koin.android.ext.android.inject

class TopTrackFragment : Fragment() {

    companion object {
        fun newInstance() =
            TopTrackFragment()
    }

    private val viewModel by inject<TopTrackViewModel> ()
    private lateinit var binding: TopTrackFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_track_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}