package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.utils.DispatcherProvider
import com.otero.tvmazeapp.domain.usecase.interfaces.GetFavoriteTvShowByIdUseCase
import kotlinx.coroutines.withContext

class GetFavoriteTvShowById(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : GetFavoriteTvShowByIdUseCase {
    override suspend fun invoke(id: Int) =
        withContext(dispatchers.io()) { tvShowRepository.getFavoriteTvShowById(id) }
}