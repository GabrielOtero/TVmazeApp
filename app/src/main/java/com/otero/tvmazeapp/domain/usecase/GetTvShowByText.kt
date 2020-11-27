package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository

class GetTvShowByText(
    private val tvShowRepository: TvShowRepository
) : GetTvShowByTextUseCase {
    override suspend fun invoke(searchText: String) = tvShowRepository.getTvShowsByText(searchText)
}