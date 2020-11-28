package com.otero.tvmazeapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.otero.tvmazeapp.R
import com.otero.tvmazeapp.domain.model.EpisodeBySeasonModel
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.ui.MainActivity
import kotlinx.android.synthetic.main.tv_show_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ID_KEY = "tvShowId"

class TvShowDetailFragment : Fragment() {

    private val viewModel by viewModel<TvShowDetailViewModel>()

    private val listAdapter by lazy {
        EpisodeListAdapter(
                cardClickListener = { }
        )
    }

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

        tv_show_list.adapter = listAdapter
    }

    private fun observeViewState() {
        viewModel.viewState.action.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is TvShowDetailViewState.Action.LoadInfo -> loadInfo(it.tvShowDetailModel)
                        is TvShowDetailViewState.Action.ShowLoading -> showLoading()
                        is TvShowDetailViewState.Action.LoadEpisodes -> showEpisodes(it.episodes)
                    }
                }
        )
    }

    private fun showEpisodes(episodes: EpisodeBySeasonModel?) {
        episode_progress.visibility = GONE
        listAdapter.submitList(episodes?.list)
    }

    private fun showLoading(show: Boolean = true) {
        if (show) {
            (activity as MainActivity).showLoading()
        } else {
            (activity as MainActivity).hideLoading()
        }
    }

    private fun loadInfo(tvShowModel: TvShowDetailModel?) {
        showLoading(false)
        tvShowModel?.let {
            Glide.with(this)
                    .load(tvShowModel.image.replace("http", "https"))
                    .into(tv_show_poster)
            tv_show_name.text = tvShowModel.name

            tv_show_genres.text = getString(R.string.tv_show_detail_genres_string,
                    tvShowModel.genres.joinToString(", "))
            tv_show_schedule.text =
                    getString(R.string.tv_show_detail_schedule_string,
                            tvShowModel.schedule.time, tvShowModel.schedule.days
                            .joinToString(", "))
            tv_show_summary.text = HtmlCompat.fromHtml(tvShowModel.summary,
                    HtmlCompat.FROM_HTML_MODE_COMPACT)

            val id = arguments?.getInt(ID_KEY)
            id?.let {
                viewModel.dispatchViewAction(TvShowDetailViewAction.LoadEpisodes(it))
            }
        }
    }
}