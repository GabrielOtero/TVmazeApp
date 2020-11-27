package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetTvShowByIdUseCase {
    suspend operator fun invoke(id: Int): Resource<TvShowDetailModel>
}