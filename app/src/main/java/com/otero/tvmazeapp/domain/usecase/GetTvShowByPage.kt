package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository

class GetTvShowByPage(
    private val tvShowRepository: TvShowRepository
) : GetTvShowByPageUseCase {
    override suspend fun invoke(page: Int) = tvShowRepository.getTvShowsByPage(page)

}