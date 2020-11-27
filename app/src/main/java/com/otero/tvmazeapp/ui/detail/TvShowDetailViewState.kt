package com.otero.tvmazeapp.ui.detail

import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.ui.base.SingleLiveEvent

class TvShowDetailViewState {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action {
        object ShowLoading : Action()
        data class LoadInfo(val tvShowDetailModel: TvShowDetailModel?) : Action()
    }
}
