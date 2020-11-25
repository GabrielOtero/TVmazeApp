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
            viewState.action.postValue(HomeViewState.Action.ShowTvShowList(getShowByPage(1).data))
        }
    }

    override fun dispatchViewAction(viewAction: HomeViewAction) {
        when (viewAction) {
            is HomeViewAction.Paginate -> paginate(viewAction.page)
        }
    }

    private fun paginate(page: Int) {
        Log.d("HomeViewModel", "Page")
    }
}