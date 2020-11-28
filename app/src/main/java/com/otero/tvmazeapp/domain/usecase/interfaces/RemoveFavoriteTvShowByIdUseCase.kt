package com.otero.tvmazeapp.domain.usecase.interfaces

interface RemoveFavoriteTvShowByIdUseCase {
    suspend operator fun invoke(id: Int)
}