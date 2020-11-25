package com.otero.tvmazeapp.ui.home

import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.ui.base.SingleLiveEvent

class HomeViewState {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action {
        object ShowLoading : Action()
        data class ShowTvShowList(val list: List<TvShowModel>?) : Action()
        class GoToTvShowDetail(val id: Int) : Action()
    }
}
