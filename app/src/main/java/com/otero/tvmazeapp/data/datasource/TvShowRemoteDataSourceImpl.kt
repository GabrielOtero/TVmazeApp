package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.mapper.TvShowDetailDtoToTvShowDetailModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowSearchDtoToTvShowModelMapper
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.reactivex.Observable
import io.reactivex.Scheduler

class TvShowRemoteDataSourceImpl(
        private val api: TvMazeApi,
        private val responseHandler: ResponseHandler,
        private val tvShowModelMapperTv: TvShowDtoToTvShowModelMapper,
        private val tvShowSearchDtoToTvShowModelMapper: TvShowSearchDtoToTvShowModelMapper,
        private val tvShowDetailDtoToTvShowDetailModelMapper: TvShowDetailDtoToTvShowDetailModelMapper,
        private val ioScheduler: Scheduler
) : TvShowRemoteDataSource {
    override suspend fun getShowsByPage(page: Int): Resource<List<TvShowModel>> {
        return try {
            val response = api.getShowsByPage(page)
            return responseHandler.handleSuccess(tvShowModelMapperTv.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>> {
        return try {
            val response = api.getTvShowsByText(searchText)
            return responseHandler.handleSuccess(tvShowSearchDtoToTvShowModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getTvShowById(id: Int): Resource<TvShowDetailModel> {
        return try {
            val response = api.getTvShowById(id)
            return responseHandler.handleSuccess(tvShowDetailDtoToTvShowDetailModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override fun getShowsByPageRx(page: Int): Observable<List<TvShowModel>> {
        return api.getShowsByPageRx(page).subscribeOn(ioScheduler).map {
            tvShowModelMapperTv.mapFrom(it)
        }
    }

}