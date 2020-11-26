package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TvShowRemoteDataSourceTest {
    private val api = mockk<TvMazeApi>()

    private val tvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(api, ResponseHandler(), TvShowDtoToTvShowModelMapper())

    @Test
    fun createGroup_returnSucessResult() = runBlocking {
        prepareScenario(listOf(TvShowDto(1)))

        tvShowRemoteDataSource.getShowsByPage(1)

        coVerify(exactly = 1) { api.getShowsByPage(1) }
    }

    private fun prepareScenario(list: List<TvShowDto> = emptyList()) {
        coEvery { api.getShowsByPage(any()) } returns list
    }
}