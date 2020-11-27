package com.otero.tvmazeapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.otero.tvmazeapp.R
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ID_KEY = "tvShowId"

class TvShowDetailFragment : Fragment() {

    private val viewModel by viewModel<TvShowDetailViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        observeViewState()
        return inflater.inflate(R.layout.tv_show_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ID_KEY)
        id?.let {
            viewModel.dispatchViewAction(TvShowDetailViewAction.Init(it))
        }
    }

    private fun observeViewState() {
        viewModel.viewState.action.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is TvShowDetailViewState.Action.LoadInfo -> loadInfo(it.tvShowDetailModel)
                        is TvShowDetailViewState.Action.ShowLoading -> showLoading()
                    }
                }
        )
    }

    private fun showLoading(show: Boolean = true) {
        if(show) {
            (activity as MainActivity).showLoading()
        }else {
            (activity as MainActivity).hideLoading()
        }
    }

    private fun loadInfo(tvShowModel: TvShowDetailModel?) {
        showLoading(false)
        Log.d("TvShowDetailFragment", tvShowModel.toString())
    }
}