package com.otero.tvmazeapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.domain.model.TvShowModel
import com.otero.tvmazeapp.domain.usecase.GetShowByPageUseCase
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

    private val getShowByPageUseCase = mockk<GetShowByPageUseCase>()
    private val viewState = spyk(HomeViewState())

    private val viewModel by lazy {
        HomeViewModel(getShowByPageUseCase, viewState)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    fun dispatchAction_paginate_actionTvShowList() = runBlockingTest {
        val list = listOf(TvShowModel(1), TvShowModel(2))
        prepareScenario(list)

        viewModel.dispatchViewAction(HomeViewAction.Paginate(0))

        assertTrue(viewModel.viewState.action.value is HomeViewState.Action.ShowTvShowList)
        assertEquals(
            list, (viewModel.viewState.action.value as HomeViewState.Action.ShowTvShowList).list
        )
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


    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1))) {
        coEvery { getShowByPageUseCase(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )
    }
}