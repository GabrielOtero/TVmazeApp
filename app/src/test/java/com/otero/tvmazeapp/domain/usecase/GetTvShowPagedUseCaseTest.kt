package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.datasource.TvShowPagingDataSource
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.PagedTvShowListModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTvShowPagedUseCaseTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private val api = mockk<TvMazeApi>()
    private val getTvShowPaged = GetTvShowPaged(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        getTvShowPaged().create()
        coVerify(exactly = 1) { tvShowRepository.getTvShowsByPage() }
    }

    private fun prepareScenario() {
        coEvery { tvShowRepository.getTvShowsByPage() } returns TvShowPagingDataSource { PagedTvShowListModel() }
    }
}