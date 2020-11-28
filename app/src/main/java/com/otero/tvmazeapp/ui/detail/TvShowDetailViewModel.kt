package com.otero.tvmazeapp.ui.detail

import androidx.lifecycle.viewModelScope
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.domain.usecase.GetEpisodeByShowUseCase
import com.otero.tvmazeapp.domain.usecase.GetTvShowByIdUseCase
import com.otero.tvmazeapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class TvShowDetailViewModel(
        private val getTvShowById: GetTvShowByIdUseCase,
        private val getEpisodeByShow: GetEpisodeByShowUseCase,
        override val viewState: TvShowDetailViewState
) : BaseViewModel<TvShowDetailViewState, TvShowDetailViewAction>() {

    override fun dispatchViewAction(viewAction: TvShowDetailViewAction) {
        when (viewAction) {
            is TvShowDetailViewAction.Init -> loadScreen(viewAction.id)
            is TvShowDetailViewAction.LoadEpisodes -> loadEpisodes(viewAction.id)
        }
    }

    private fun loadEpisodes(id: Int) {
        viewModelScope.launch {
            val result = getEpisodeByShow(id)
            if (result.status == Status.SUCCESS) {
                viewState.action.postValue(TvShowDetailViewState.Action.LoadEpisodes(result.data))
            }
        }
    }

    private fun loadScreen(id: Int) {
        viewState.action.postValue(TvShowDetailViewState.Action.ShowLoading)
        viewModelScope.launch {
            val result = getTvShowById(id)
            if (result.status == Status.SUCCESS) {
                viewState.action.postValue(TvShowDetailViewState.Action.LoadInfo(result.data))
            }
        }
    }
}