package com.mistpaag.lastfm.trainee.views.main.topArtist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.adapters.TopArtistAdapter
import com.mistpaag.lastfm.trainee.databinding.TopArtistFragmentBinding
import com.mistpaag.lastfm.trainee.utils.smoothSnapToPosition
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject


class TopArtistFragment : Fragment(), SearchView.OnQueryTextListener {

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

        val adapter = TopArtistAdapter{position ->
            viewModel.topArtistList.value?.let {artistList ->
                val topArtist = artistList[position]
//                findNavController().navigate(TopArtistFragmentDirections.actionTopArtistFragment2ToDetailWebViewFragment(topArtist.url, topArtist.name))
            }
        }
        binding.topArtistRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.topArtistRecycler.adapter = adapter

        binding.topArtistRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (binding.textView.text.isNullOrEmpty()) return
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE ) {
                    viewModel.needOtherPage()
                }
            }
        })

        viewModel.topArtistList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
            adapter.notifyDataSetChanged()
        })


        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                val layoutManager = binding.topArtistRecycler.layoutManager
                val current = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (!binding.textView.text.isNullOrEmpty()){
                    binding.topArtistRecycler.smoothSnapToPosition(current+2)
                }

            }
        })

        binding.search.setOnSearchClickListener{
            binding.textView.text = ""
        }


        binding.search.setOnCloseListener{
            binding.textView.text = getString(R.string.topartist)
            return@setOnCloseListener false
        }

        binding.search.setOnQueryTextListener(this)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchTopArtists()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let {
            viewModel.searchTopArtists(it)
        }
        return false
    }

}