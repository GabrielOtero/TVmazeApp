package com.otero.tvmazeapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otero.tvmazeapp.R
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.ui.MainActivity
import com.otero.tvmazeapp.ui.detail.TvShowDetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()
    var loadingRecyclerView = false

    private val listAdapter by lazy {
        TvShowAdapter {
            homeViewModel.dispatchViewAction(HomeViewAction.CardClick(it))
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        observeViewState()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_show_list.adapter = listAdapter
        tv_show_list.addOnScrollListener(onScrollListener())
    }

    private fun observeViewState() {
        homeViewModel.viewState.action.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is HomeViewState.Action.ShowLoading -> (activity as MainActivity).showLoading()
                        is HomeViewState.Action.ShowTvShowList -> showTvShowList(it.list)
                        is HomeViewState.Action.GoToTvShowDetail -> goToTvShowDetail(it.id)
                    }
                }
        )
    }

    private fun goToTvShowDetail(id: Int) {
        (activity as MainActivity).goTo(TvShowDetailFragment.newInstance(id))
    }

    private fun showTvShowList(list: List<TvShowModel>?) {
        (activity as MainActivity).hideLoading()
        loadingRecyclerView = false
        listAdapter.submitList(list)
    }

    private fun onScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            var pastVisibleItems = 0
            var visibleItemCount = 0
            var totalItemCount = 0
            var page: Int = 1
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                visibleItemCount = tv_show_list.layoutManager?.childCount ?: 0
                totalItemCount = tv_show_list.layoutManager?.itemCount ?: 0
                pastVisibleItems =
                        (tv_show_list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (!loadingRecyclerView) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount / 2) {
                        loadingRecyclerView = true
                        homeViewModel.dispatchViewAction(HomeViewAction.Paginate(page++))
                    }
                }
            }
        }
    }
}