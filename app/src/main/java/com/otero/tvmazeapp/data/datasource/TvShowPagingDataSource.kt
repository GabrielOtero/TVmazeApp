package com.otero.tvmazeapp.data.datasource

import androidx.paging.PageKeyedDataSource
import com.otero.tvmazeapp.domain.model.PagedTvShowListModel
import com.otero.tvmazeapp.domain.model.TvShowModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowPagingDataSource(
        private val getTvShowList: suspend (Int) -> PagedTvShowListModel
) : PageKeyedDataSource<Int, TvShowModel>() {

    private var currentPage: Int = 0

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, TvShowModel>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val pagedTvShowListModel = getTvShowList(currentPage)
            callback.onResult(pagedTvShowListModel.list, null, getNextPage(pagedTvShowListModel))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val pagedTvShowListModel = getTvShowList(params.key)
            val previousPage = currentPage - 1
            callback.onResult(pagedTvShowListModel.list, previousPage)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val pagedTvShowListModel = getTvShowList(params.key)
            callback.onResult(pagedTvShowListModel.list, getNextPage(pagedTvShowListModel))
        }
    }

    private fun getNextPage(pagedTvShowListModel: PagedTvShowListModel) = if (pagedTvShowListModel.hasMore) ++currentPage else null
}