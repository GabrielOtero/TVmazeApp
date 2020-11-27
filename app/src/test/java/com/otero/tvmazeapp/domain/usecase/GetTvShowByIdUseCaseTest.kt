package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.repository.TvShowRepository
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
class GetTvShowByIdUseCaseTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getShowById = GetTvShowById(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()

        getShowById(id)

        coVerify(exactly = 1) { tvShowRepository.getTvShowById(id) }
    }

    private fun prepareScenario(list: TvShowDetailModel = TvShowDetailModel(1, "", "")) {
        coEvery { tvShowRepository.getTvShowById(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )
    }
}