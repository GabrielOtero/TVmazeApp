package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {
    override suspend fun getTvShowsByPage(page: Int) = tvShowRemoteDataSource.getShowsByPage(page)
    override suspend fun getTvShowsByText(searchText: String) =
        tvShowRemoteDataSource.getTvShowsByText(searchText)
}