package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.utils.DispatcherProvider
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.interfaces.SaveFavoriteTvShowUseCase
import kotlinx.coroutines.withContext

class SaveFavoriteTvShow(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : SaveFavoriteTvShowUseCase {
    override suspend fun invoke(tvShow: TvShowModel) =
        withContext(dispatchers.io()) { tvShowRepository.saveFavoriteTvShow(tvShow) }

}