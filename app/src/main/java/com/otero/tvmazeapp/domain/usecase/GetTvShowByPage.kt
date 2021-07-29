package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowByPageUseCase
import io.reactivex.Observable

class GetTvShowByPage(
    private val tvShowRepository: TvShowRepository
) : GetTvShowByPageUseCase {
    override suspend fun invoke(page: Int) = tvShowRepository.getTvShowsByPage(page)
    override suspend fun invokeRx(page: Int) = tvShowRepository.getTvShowsByPageRx(page)
}