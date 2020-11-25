package com.otero.tvmazeapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.otero.tvmazeapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        observeViewState()
        return root
    }

    private fun observeViewState() {
        homeViewModel.viewState.action.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is HomeViewState.Action.ShowLoading -> {
                        Log.d("HomeFragment", "Loading")
                    }
                    is HomeViewState.Action.ShowTvShowList -> {
                        Log.d("HomeFragment", it.list.toString())
                    }
                }
            }
        )
    }
}