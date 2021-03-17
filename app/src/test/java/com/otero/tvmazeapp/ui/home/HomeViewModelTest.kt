package com.otero.tvmazeapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowPagedUseCase
import com.otero.tvmazeapp.domain.usecase.interfaces.GetTvShowByTextUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val getShowByPageUseCase = mockk<GetTvShowPagedUseCase>()
    private val getTvShowByTextUseCase = mockk<GetTvShowByTextUseCase>()
    private val tvShowRepository = mockk<TvShowRepository>()
    private val viewState = spyk(HomeViewState())

    private val viewModel by lazy {
        HomeViewModel(getShowByPageUseCase, getTvShowByTextUseCase, viewState)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun dispatchAction_cardClick_actionTvShowList() = runBlockingTest {
        val id = 1
        prepareScenario()

        viewModel.dispatchViewAction(HomeViewAction.CardClick(id))

        assertTrue(viewModel.viewState.action.value is HomeViewState.Action.GoToTvShowDetail)
        assertEquals(
            id, (viewModel.viewState.action.value as HomeViewState.Action.GoToTvShowDetail).id
        )
    }

    @Test
    fun dispatchAction_textSearchClick_emptyResult_actionEmptyState() = runBlockingTest {
        prepareScenario(emptyList())

        viewModel.dispatchViewAction(HomeViewAction.TextSearchClick("asdfg"))

        assertTrue(viewModel.viewState.action.value is HomeViewState.Action.ShowEmptyState)
    }

    @Test
    fun dispatchAction_textSearchClick_actionTvShowList() = runBlockingTest {
        val list = listOf(TvShowModel(1, "", ""), TvShowModel(2, "", ""))
        prepareScenario(list)

        viewModel.dispatchViewAction(HomeViewAction.TextSearchClick("asdfg"))

        assertTrue(viewModel.viewState.action.value is HomeViewState.Action.ShowTvShowListByText)
        assertEquals(
            list,
            (viewModel.viewState.action.value as HomeViewState.Action.ShowTvShowListByText).list
        )
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1, "", ""))) {
        coEvery { getTvShowByTextUseCase(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )

        coEvery { getShowByPageUseCase() } returns object : DataSource.Factory<Int, TvShowModel>() {
            override fun create() = tvShowRepository.getTvShowsByPage()
        }
    }
}