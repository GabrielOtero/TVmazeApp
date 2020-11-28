package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.utils.DispatcherProvider
import com.otero.tvmazeapp.domain.usecase.interfaces.RemoveFavoriteTvShowByIdUseCase
import kotlinx.coroutines.withContext

class RemoveFavoriteTvShowById(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : RemoveFavoriteTvShowByIdUseCase {
    override suspend fun invoke(id: Int) =
        withContext(dispatchers.io()) { tvShowRepository.removeFavoriteTvShowById(id) }
}