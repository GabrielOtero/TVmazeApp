package com.otero.tvmazeapp.ui.home

sealed class HomeViewAction {
    data class CardClick(val id: Int) : HomeViewAction()
    data class TextSearchClick(val textSearch: String) : HomeViewAction()
}
