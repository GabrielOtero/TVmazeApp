package com.otero.tvmazeapp.domain.usecase.interfaces

import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetFavoriteListUseCase {
    suspend operator fun invoke(): List<TvShowModel>?
}