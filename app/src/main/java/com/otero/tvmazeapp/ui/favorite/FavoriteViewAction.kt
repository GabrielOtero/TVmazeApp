package com.otero.tvmazeapp.ui.favorite

sealed class FavoriteViewAction {
    data class CardClick(val id: Int) : FavoriteViewAction()
    object LoadFavorites : FavoriteViewAction()
}
