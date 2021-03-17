package com.otero.tvmazeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowPagedUseCase
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowByTextUseCase
import com.otero.tvmazeapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTvShowPaged: GetTvShowPagedUseCase,
    private val getTvShowByTextUseCase: GetTvShowByTextUseCase,
    override val viewState: HomeViewState
) : BaseViewModel<HomeViewState, HomeViewAction>() {

    val tvShowList: LiveData<PagedList<TvShowModel>>

    init {
        viewState.action.postValue(HomeViewState.Action.ShowLoading)


        val pagingConfig = PagedList.Config.Builder().setPageSize(30).build()
        tvShowList = LivePagedListBuilder(getTvShowPaged(), pagingConfig).build()
    }

    override fun dispatchViewAction(viewAction: HomeViewAction) {
        when (viewAction) {
            is HomeViewAction.CardClick -> cardClick(viewAction.id)
            is HomeViewAction.TextSearchClick -> textSearch(viewAction.textSearch)
        }
    }

    private fun textSearch(textSearch: String) {
        viewState.action.postValue(HomeViewState.Action.ClearList)

        viewModelScope.launch {
                val result = getTvShowByTextUseCase(textSearch)
                if (result.data.isNullOrEmpty()) {
                    viewState.action.postValue(HomeViewState.Action.ShowEmptyState)
                } else {
                    viewState.action.postValue(HomeViewState.Action.ShowTvShowListByText(result.data))
                }
            }
    }

    private fun cardClick(id: Int) {
        viewState.action.postValue(HomeViewState.Action.GoToTvShowDetail(id))
    }
}