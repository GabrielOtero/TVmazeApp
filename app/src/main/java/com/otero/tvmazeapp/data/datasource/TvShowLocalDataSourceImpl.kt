package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.database.AppDatabase
import com.otero.tvmazeapp.data.dbo.TvShowDbo
import com.otero.tvmazeapp.data.mapper.TvShowDboToTvShowModelMapper
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowLocalDataSourceImpl(
    private val appDatabase: AppDatabase,
    private val tvShowDboToTvShowModel: TvShowDboToTvShowModelMapper
) : TvShowLocalDataSource {
    override suspend fun insertTvShow(tvShowModel: TvShowModel) {
        appDatabase.tvShowDao().insert(
            TvShowDbo(
                id = tvShowModel.id,
                name = tvShowModel.name,
                image = tvShowModel.image
            )
        )
    }

    override suspend fun getById(id: Int): TvShowModel? {
        val byId = appDatabase.tvShowDao().getById(id)
        return byId?.let {
            tvShowDboToTvShowModel.mapFrom(it)
        }
    }

    override suspend fun removeTvShow(id: Int) {
        appDatabase.tvShowDao().delete(id)
    }

    override suspend fun getAllTvShow(): List<TvShowModel> {
        return appDatabase.tvShowDao().getAll().map {
            tvShowDboToTvShowModel.mapFrom(it)
        }
    }
}