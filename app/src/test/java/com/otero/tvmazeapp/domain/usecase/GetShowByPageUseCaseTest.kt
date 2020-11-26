package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.GetShowByPage
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class GetShowByPageUseCaseTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getShowByPage = GetShowByPage(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val page = Random(currentTime).nextInt()

        getShowByPage(page)

        coVerify(exactly = 1) { tvShowRepository.getShowsByPage(page) }
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1))) {
        coEvery { tvShowRepository.getShowsByPage(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )
    }
}