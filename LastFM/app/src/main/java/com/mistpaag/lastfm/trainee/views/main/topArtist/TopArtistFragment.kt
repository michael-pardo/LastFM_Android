package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.adapters.TopArtistAdapter
import com.mistpaag.lastfm.trainee.databinding.TopArtistFragmentBinding
import com.mistpaag.lastfm.trainee.models.TopArtist
import org.koin.android.ext.android.inject

class TopArtistFragment : Fragment() {

    companion object {
        fun newInstance() =
            TopArtistFragment()
    }

    private lateinit var binding: TopArtistFragmentBinding
    private val viewModel by inject<TopArtistViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_artist_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = TopArtistAdapter{

        }
        binding.topArtistRecycler.adapter = adapter

        viewModel.topArtistList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchTopArtists()
    }

}