package com.otero.tvmazeapp.ui.favorite

import androidx.lifecycle.viewModelScope
import com.otero.tvmazeapp.domain.usecase.interfaces.GetFavoriteListUseCase
import com.otero.tvmazeapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteListUseCase: GetFavoriteListUseCase,
    override val viewState: FavoriteViewState
) : BaseViewModel<FavoriteViewState, FavoriteViewAction>() {

    override fun dispatchViewAction(viewAction: FavoriteViewAction) {
        when (viewAction) {
            is FavoriteViewAction.CardClick -> cardClick(viewAction.id)
            FavoriteViewAction.LoadFavorites -> loadFavorites()
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            val list = getFavoriteListUseCase()
            if (list.isNullOrEmpty()) {
                viewState.action.postValue(FavoriteViewState.Action.ShowEmptyState)
            } else {
                viewState.action.postValue(FavoriteViewState.Action.ShowTvShowList(list))
            }
        }
    }

    private fun cardClick(id: Int) {
        viewState.action.postValue(FavoriteViewState.Action.GoToTvShowDetail(id))
    }

}

