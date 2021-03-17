package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.CoroutinesTestRule
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetFavoriteListUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getFavoriteList = GetFavoriteList(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()

        val favoriteList = getFavoriteList()
        assertNotNull(favoriteList)
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1, "", ""))) {
    }
}