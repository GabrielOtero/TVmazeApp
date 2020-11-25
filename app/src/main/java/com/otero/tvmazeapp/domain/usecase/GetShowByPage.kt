package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.ShowRepository

class GetShowByPage(
    private val showRepository: ShowRepository
) : GetShowByPageUseCase {
    override suspend fun invoke(page: Int) = showRepository.getShowsByPage(page)

}