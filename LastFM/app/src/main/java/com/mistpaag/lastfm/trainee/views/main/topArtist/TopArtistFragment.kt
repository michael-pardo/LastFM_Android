package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.adapters.TopArtistAdapter
import com.mistpaag.lastfm.trainee.databinding.TopArtistFragmentBinding
import com.mistpaag.lastfm.trainee.models.database.TopArtist
import com.mistpaag.lastfm.trainee.utils.isLandsCape
import com.mistpaag.lastfm.trainee.utils.smoothSnapToPosition
import com.mistpaag.lastfm.trainee.views.main.SharedActivityViewModel
import org.koin.android.ext.android.inject


class TopArtistFragment : Fragment() {

    companion object {
        fun newInstance() =
            TopArtistFragment()
    }

    private lateinit var binding: TopArtistFragmentBinding
    private val viewModel by inject<TopArtistViewModel> ()
    private val sharedViewModel by inject<SharedActivityViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_artist_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = TopArtistAdapter{topArtist ->
            goToDetail(topArtist)
        }
        if (requireActivity().isLandsCape()) {
            binding.topArtistRecycler.layoutManager = GridLayoutManager(context, 3)
        } else {
            binding.topArtistRecycler.layoutManager = GridLayoutManager(context, 2)
        }

        binding.topArtistRecycler.adapter = adapter

        binding.topArtistRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE ) {
                    if (!sharedViewModel.isSearching.value!!){
                        viewModel.needOtherPage()
                    }
                }
            }
        })

        viewModel.topArtistList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
        })

        sharedViewModel.searchText.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                viewModel.searchTopArtists(it)
            }else{
                viewModel.setTopArtists()
            }
        })

        sharedViewModel.isSearching.observe(viewLifecycleOwner, Observer {isSearching ->
            if (isSearching) {
                binding.topArtistRecycler.smoothSnapToPosition(0)
            }else{
                viewModel.setTopArtists()
            }
        })
        viewModel.loadInitData()
        viewModel.fetchTopArtists()
        return binding.root
    }

    private fun goToDetail(topArtist:TopArtist){
        findNavController().navigate(TopArtistFragmentDirections.actionTopArtistFragmentToDetailWebActivity(topArtist.url,topArtist.name))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do something
                requireActivity().moveTaskToBack(true)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}