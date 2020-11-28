package com.otero.tvmazeapp.domain.usecase.interfaces

import com.otero.tvmazeapp.domain.model.TvShowModel

interface SaveFavoriteTvShowUseCase {
    suspend operator fun invoke(tvShow: TvShowModel)
}
