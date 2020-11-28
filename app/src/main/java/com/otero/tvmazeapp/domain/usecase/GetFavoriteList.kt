package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.utils.DispatcherProvider
import com.otero.tvmazeapp.domain.usecase.interfaces.GetFavoriteListUseCase
import kotlinx.coroutines.withContext

class GetFavoriteList(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : GetFavoriteListUseCase {
    override suspend fun invoke() =
        withContext(dispatchers.io()) { tvShowRepository.getAllFavoriteTvShow() }
}