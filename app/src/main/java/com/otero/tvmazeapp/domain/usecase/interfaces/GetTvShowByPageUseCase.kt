package com.otero.tvmazeapp.domain.usecase.interfaces

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.reactivex.Observable

interface GetTvShowByPageUseCase {
    suspend operator fun invoke(page: Int = 0): Resource<List<TvShowModel>>
    suspend fun invokeRx(page: Int = 0): Observable<List<TvShowModel>>
}