package com.otero.tvmazeapp.domain.usecase.interfaces

import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetFavoriteTvShowByIdUseCase {
    suspend operator fun invoke(id: Int): TvShowModel?
}
