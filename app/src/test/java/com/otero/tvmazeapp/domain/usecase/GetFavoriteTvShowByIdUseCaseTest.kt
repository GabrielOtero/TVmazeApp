package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.CoroutinesTestRule
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class GetFavoriteTvShowByIdUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getFavoriteTvShowById = GetFavoriteTvShowById(tvShowRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()
        getFavoriteTvShowById(id)

        coVerify(exactly = 1) { tvShowRepository.getFavoriteTvShowById(id) }
    }

    private fun prepareScenario(list: TvShowModel = TvShowModel(1, "", "")) {
        coEvery { tvShowRepository.getFavoriteTvShowById(any()) } returns list
    }
}