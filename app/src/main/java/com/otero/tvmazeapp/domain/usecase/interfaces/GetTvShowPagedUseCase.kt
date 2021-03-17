package com.otero.tvmazeapp.domain.usecase.interfaces

import androidx.paging.DataSource
import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetTvShowPagedUseCase {
    operator fun invoke(): DataSource.Factory<Int, TvShowModel>
}