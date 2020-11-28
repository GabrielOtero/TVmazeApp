package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.mapper.EpisodeDtoToEpisodeModelMapper
import com.otero.tvmazeapp.domain.model.EpisodeModel

class EpisodeRemoteDataSourceImpl(
        private val api: TvMazeApi,
        private val responseHandler: ResponseHandler,
        private val episodeModelMapper: EpisodeDtoToEpisodeModelMapper
) : EpisodeRemoteDataSource {
    override suspend fun getTvEpisodesByShowId(id: Int): Resource<List<EpisodeModel>> {
        return try {
            val response = api.getTvEpisodesByShowId(id)
            return responseHandler.handleSuccess(episodeModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}