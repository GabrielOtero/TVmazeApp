package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.CoroutinesTestRule
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveFavoriteTvShowCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val saveFavoriteTvShow = SaveFavoriteTvShow(tvShowRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        val tvShow = TvShowModel(1, "", "")
        prepareScenario()

        saveFavoriteTvShow(tvShow)

        coVerify(exactly = 1) { tvShowRepository.saveFavoriteTvShow(tvShow) }
    }

    private fun prepareScenario() {
        coEvery { tvShowRepository.saveFavoriteTvShow(any()) } just runs
    }
}