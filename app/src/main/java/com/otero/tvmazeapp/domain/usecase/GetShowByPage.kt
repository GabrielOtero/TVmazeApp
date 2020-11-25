package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository

class GetShowByPage(
    private val tvShowRepository: TvShowRepository
) : GetShowByPageUseCase {
    override suspend fun invoke(page: Int) = tvShowRepository.getShowsByPage(page)

}