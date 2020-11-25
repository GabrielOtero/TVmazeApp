package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.datasource.ShowRemoteDataSource

class ShowRepositoryImpl(
    private val showRemoteDataSource: ShowRemoteDataSource
) : ShowRepository {
    override suspend fun getShowsByPage(page: Int) = showRemoteDataSource.getShowsByPage(page)
}