package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowByPageUseCase

class GetTvShowByPage(
    private val tvShowRepository: TvShowRepository
) : GetTvShowByPageUseCase {
    override suspend fun invoke(page: Int) = tvShowRepository.getTvShowsByPage(page)

}