package com.otero.tvmazeapp.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.otero.tvmazeapp.domain.usecase.GetShowByPageUseCase
import com.otero.tvmazeapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getShowByPage: GetShowByPageUseCase,
    override val viewState: HomeViewState
) : BaseViewModel<HomeViewState, HomeViewAction>() {

    init {
        viewState.action.postValue(HomeViewState.Action.ShowLoading)

        viewModelScope.launch {
            viewState.action.postValue(HomeViewState.Action.ShowTvShowList(getShowByPage().data))
        }
    }

    override fun dispatchViewAction(viewAction: HomeViewAction) {
        when (viewAction) {
            is HomeViewAction.Paginate -> paginate(viewAction.page)
            is HomeViewAction.CardClick -> cardClick(viewAction.id)
        }
    }

    private fun cardClick(id: Int) {
        viewState.action.postValue(HomeViewState.Action.GoToTvShowDetail(id))
    }

    private fun paginate(page: Int) {
        viewModelScope.launch {
            viewState.action.postValue(HomeViewState.Action.ShowTvShowList(getShowByPage(page).data))
        }
    }
}