package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.mapper.ShowDtoToShowModelMapper
import com.otero.tvmazeapp.domain.model.ShowModel

class ShowRemoteDataSourceImpl(
    private val api: TvMazeApi,
    private val responseHandler: ResponseHandler,
    private val showModelMapper: ShowDtoToShowModelMapper
) : ShowRemoteDataSource {
    override suspend fun getShowsByPage(page: Int): Resource<List<ShowModel>> {
        return try {
            val response = api.getShowsByPage(page)
            return responseHandler.handleSuccess(showModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}