package com.otero.tvmazeapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.otero.tvmazeapp.R

const val ID_KEY = "id"

class TvShowDetailFragment : Fragment() {

    private lateinit var viewModel: TvShowDetailViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_show_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TvShowDetailFragment", arguments?.getInt(ID_KEY).toString())

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TvShowDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(id: Int): TvShowDetailFragment {
            val tvShowDetailFragment = TvShowDetailFragment()
            tvShowDetailFragment.arguments = bundleOf(Pair(ID_KEY, id))
            return tvShowDetailFragment
        }
    }

}