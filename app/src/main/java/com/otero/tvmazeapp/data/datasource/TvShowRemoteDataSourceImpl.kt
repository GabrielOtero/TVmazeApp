package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowRemoteDataSourceImpl(
    private val api: TvMazeApi,
    private val responseHandler: ResponseHandler,
    private val tvShowModelMapperTv: TvShowDtoToTvShowModelMapper
) : TvShowRemoteDataSource {
    override suspend fun getShowsByPage(page: Int): Resource<List<TvShowModel>> {
        return try {
            val response = api.getShowsByPage(page)
            return responseHandler.handleSuccess(tvShowModelMapperTv.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}