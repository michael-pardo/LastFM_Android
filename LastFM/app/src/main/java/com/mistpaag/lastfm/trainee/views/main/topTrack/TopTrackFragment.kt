package com.mistpaag.lastfm.trainee.views.main.topTrack

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.adapters.TopTrackAdapter
import com.mistpaag.lastfm.trainee.databinding.TopTrackFragmentBinding
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.utils.smoothSnapToPosition
import com.mistpaag.lastfm.trainee.views.main.SharedActivityViewModel
import org.koin.android.ext.android.inject

class TopTrackFragment : Fragment() {

    companion object {
        fun newInstance() =
            TopTrackFragment()
    }

    private val viewModel by inject<TopTrackViewModel> ()
    private val sharedViewModel by inject<SharedActivityViewModel> ()
    private lateinit var binding: TopTrackFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_track_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = TopTrackAdapter{ topTrack ->
            goToDetail(topTrack)
        }
        binding.recyclerTopTracks.layoutManager = GridLayoutManager(context, 1)
        binding.recyclerTopTracks.adapter = adapter

        binding.recyclerTopTracks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE ) {
                    if (!sharedViewModel.isSearching.value!!){
                        viewModel.needOtherPage()
                    }
                }
            }
        })

        viewModel.topTrackstList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
        })

        sharedViewModel.searchText.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                viewModel.searchTopArtists(it)
            }else{
                viewModel.setTopTracks()
            }
        })

        sharedViewModel.isSearching.observe(viewLifecycleOwner, Observer {isSearching ->
            if (isSearching) {
                binding.recyclerTopTracks.smoothSnapToPosition(0)
            }else{
                viewModel.setTopTracks()
            }
        })
        viewModel.loadInitData()
        viewModel.fetchTopTracks()

        return binding.root
    }

    private fun goToDetail(topTrack: TopTrack) {
        findNavController().navigate(TopTrackFragmentDirections.actionTopTrackFragmentToDetailTopTrackActivity(topTrack.name))
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }



}