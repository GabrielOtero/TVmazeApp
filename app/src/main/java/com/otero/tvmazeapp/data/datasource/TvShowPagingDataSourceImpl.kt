package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.domain.model.TvShowModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowPagingDataSourceImpl(
    private val api: TvMazeApi,
    private val tvShowModelMapperTv: TvShowDtoToTvShowModelMapper,
    private val responseHandler: ResponseHandler
) : TvShowPagingDataSource() {

    private var currentPage: Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShowModel>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val articlesPageList = getTvShowList(currentPage)
            callback.onResult(articlesPageList, null, getNextPage())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val articlesPageList = getTvShowList(params.key)
            val previousPage = currentPage - 1
            callback.onResult(articlesPageList, previousPage)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val articlesPageList = getTvShowList(params.key)
            callback.onResult(articlesPageList, getNextPage())
        }
    }

    private suspend fun loadTvShow(page: Int): Resource<List<TvShowModel>> {
        return try {
            val response = api.getShowsByPage(page)
            return responseHandler.handleSuccess(tvShowModelMapperTv.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private suspend fun getTvShowList(page: Int): List<TvShowModel> {
        return withContext(Dispatchers.IO) {
            val result = loadTvShow(page)
            if (result.status == Status.SUCCESS)
                result.data ?: emptyList()
            else
                emptyList()
        }
    }


    private fun getNextPage() = ++currentPage
}