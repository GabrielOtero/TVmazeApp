package com.otero.tvmazeapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.interfaces.GetFavoriteListUseCase
import com.otero.tvmazeapp.ui.base.BaseViewModel

class FavoriteViewModel(
    private val getFavoriteListUseCase: GetFavoriteListUseCase,
    override val viewState: FavoriteViewState
) : BaseViewModel<FavoriteViewState, FavoriteViewAction>() {

    val tvShowList: LiveData<PagedList<TvShowModel>>

    init {

        val pagingConfig = PagedList.Config.Builder().setPageSize(30).build()
        tvShowList = LivePagedListBuilder(getFavoriteListUseCase(), pagingConfig).build()
    }

    override fun dispatchViewAction(viewAction: FavoriteViewAction) {
        when (viewAction) {
            is FavoriteViewAction.CardClick -> cardClick(viewAction.id)
        }
    }


    private fun cardClick(id: Int) {
        viewState.action.postValue(FavoriteViewState.Action.GoToTvShowDetail(id))
    }

}

