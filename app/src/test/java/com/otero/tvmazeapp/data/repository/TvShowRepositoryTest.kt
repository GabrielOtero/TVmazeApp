package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class TvShowRepositoryTest {

    private val tvShowRemoteDataSource = mockk<TvShowRemoteDataSource>()
    private val tvShowRepository = TvShowRepositoryImpl(tvShowRemoteDataSource)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val page = Random(currentTime).nextInt()

        tvShowRepository.getTvShowsByPage(page)

        coVerify(exactly = 1) { tvShowRemoteDataSource.getShowsByPage(page) }
    }

    @Test
    fun callGetShowByText_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val searchText = "Text"

        tvShowRepository.getTvShowsByText(searchText)

        coVerify(exactly = 1) { tvShowRemoteDataSource.getTvShowsByText(searchText) }
    }

    @Test
    fun callGetShowById_shouldReturnTvShowDetailModel() = runBlockingTest {
        prepareScenario()
        val id = kotlin.random.Random(currentTime).nextInt()

        tvShowRepository.getTvShowById(id)

        coVerify(exactly = 1) { tvShowRemoteDataSource.getTvShowById(id) }
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1, "", "")),
    detail : TvShowDetailModel = TvShowDetailModel(1, "", "")) {
        coEvery { tvShowRemoteDataSource.getShowsByPage(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )

        coEvery { tvShowRemoteDataSource.getTvShowsByText(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )

        coEvery { tvShowRemoteDataSource.getTvShowById(any()) } returns Resource(
                status = Status.SUCCESS,
                data = detail,
                message = null
        )
    }
}