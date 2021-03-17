package com.otero.tvmazeapp.domain.usecase

import androidx.paging.DataSource
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowPagedUseCase

class GetTvShowPaged(
    private val tvShowRepository: TvShowRepository
) : GetTvShowPagedUseCase {
    override fun invoke() = object : DataSource.Factory<Int, TvShowModel>() {
        override fun create() = tvShowRepository.getTvShowsByPage()
    }
}