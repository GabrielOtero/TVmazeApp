package com.otero.tvmazeapp.ui.favorite

import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.ui.base.SingleLiveEvent

class FavoriteViewState {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action {
        data class ShowTvShowList(val list: List<TvShowModel>) : Action()
        data class GoToTvShowDetail(val id: Int) : Action()

        object ShowLoading : Action()
        object ShowEmptyState : Action()
    }
}
