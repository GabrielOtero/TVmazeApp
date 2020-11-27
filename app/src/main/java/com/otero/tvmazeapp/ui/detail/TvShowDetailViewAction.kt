package com.otero.tvmazeapp.ui.detail

sealed class TvShowDetailViewAction {
    data class Init(val id: Int) : TvShowDetailViewAction()
}
