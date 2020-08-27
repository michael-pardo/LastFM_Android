package com.mistpaag.lastfm.trainee.views.main.detailWebview

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.DetailWebViewFragmentBinding
import com.mistpaag.lastfm.trainee.views.main.SharedActivityViewModel
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject

class DetailWebViewFragment : Fragment() {

    companion object {
        fun newInstance() =
            DetailWebViewFragment()
    }


    private val sharedViewModel by inject<SharedActivityViewModel> ()

    private val viewModel by inject<DetailWebViewViewModel> ()
    private lateinit var binding: DetailWebViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_web_view_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        arguments?.let {
            val url = DetailWebViewFragmentArgs.fromBundle(
                it
            ).url
            binding.detailWebview.loadUrl(url)
            val name = DetailWebViewFragmentArgs.fromBundle(
                it
            ).tittle
            binding.tittleWeb.text = name
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do something
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}