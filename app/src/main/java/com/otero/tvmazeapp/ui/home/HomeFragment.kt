package com.otero.tvmazeapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.otero.tvmazeapp.R
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.ui.MainActivity
import com.otero.tvmazeapp.ui.detail.ID_KEY
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {

    private val homeViewModel by viewModel<HomeViewModel>()

    private val listAdapter by lazy {
        TvShowAdapter(
            cardClickListener = { homeViewModel.dispatchViewAction(HomeViewAction.CardClick(it)) },
            loadImageCallback = { image, imageView -> loadCardImage(image, imageView) }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        observeViewState()
        observeTvShowsList()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_show_list.adapter = listAdapter
        search_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            search_button -> searchByText(search_serie_by_name.text.toString())
        }
    }

    private fun observeViewState() {
        homeViewModel.viewState.action.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is HomeViewState.Action.GoToTvShowDetail -> goToTvShowDetail(it.id)
                    is HomeViewState.Action.ShowEmptyState -> showEmptyState()
                    is HomeViewState.Action.ClearList -> clearList()
                    is HomeViewState.Action.ShowTvShowListByText -> showTvShowListByText(it.list)
                }
            }
        )
    }

    private fun observeTvShowsList() {
        homeViewModel.tvShowList.observe(viewLifecycleOwner, Observer { articles ->
            listAdapter.submitList(articles)
        })
    }

    private fun showTvShowListByText(list: List<TvShowModel>?) {
        (activity as MainActivity).hideLoading()
    }

    private fun clearList() {
        (activity as MainActivity).showLoading()
    }

    private fun showEmptyState() {
        (activity as MainActivity).hideLoading()
        empty_state_label.visibility = VISIBLE
    }

    private fun goToTvShowDetail(id: Int) {
        findNavController().navigate(R.id.action_title_to_about, bundleOf(Pair(ID_KEY, id)))
    }

    private fun searchByText(textSearch: String) {
        homeViewModel.dispatchViewAction(HomeViewAction.TextSearchClick(textSearch))
    }

    private fun loadCardImage(image: String, imageView: ImageView) {
        Glide
            .with(this)
            .load(image)
            .into(imageView)
    }
}