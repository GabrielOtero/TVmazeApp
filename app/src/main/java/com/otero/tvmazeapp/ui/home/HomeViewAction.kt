package com.otero.tvmazeapp.ui.home

sealed class HomeViewAction {
    data class Paginate(val page: Int) : HomeViewAction()
}
