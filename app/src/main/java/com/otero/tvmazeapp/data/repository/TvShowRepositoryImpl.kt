package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {
    override suspend fun getShowsByPage(page: Int) = tvShowRemoteDataSource.getShowsByPage(page)
}