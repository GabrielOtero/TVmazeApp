package com.otero.tvmazeapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.domain.model.EpisodeBySeasonModel
import com.otero.tvmazeapp.domain.model.EpisodeItemResult
import com.otero.tvmazeapp.domain.model.ScheduleModel
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.usecase.GetEpisodeByShowUseCase
import com.otero.tvmazeapp.domain.usecase.GetTvShowByIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class TvShowDetailViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val getShowByIdUseCase = mockk<GetTvShowByIdUseCase>()
    private val getEpisodeByShow = mockk<GetEpisodeByShowUseCase>()
    private val viewState = spyk(TvShowDetailViewState())

    private val viewModel by lazy {
        TvShowDetailViewModel(getShowByIdUseCase, getEpisodeByShow, viewState)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun dispatchAction_init_actionTvShowDetail() = runBlockingTest {
        val id = Random(currentTime).nextInt()
        val detail = TvShowDetailModel(id, "", "", emptyList(), ScheduleModel("", emptyList()), "")
        prepareScenario(detail)

        viewModel.dispatchViewAction(TvShowDetailViewAction.Init(id))

        Assert.assertTrue(viewModel.viewState.action.value is TvShowDetailViewState.Action.LoadInfo)
        Assert.assertEquals(
                detail,
                (viewModel.viewState.action.value as TvShowDetailViewState.Action.LoadInfo).tvShowDetailModel)
    }

    @Test
    fun dispatchAction_loadEpisodes_actionTvShowEpisodes() = runBlockingTest {
        val id = Random(currentTime).nextInt()
        val detail = TvShowDetailModel(id, "", "", emptyList(), ScheduleModel("", emptyList()), "")
        val episodeBySeasonModel = EpisodeBySeasonModel(mutableListOf(EpisodeItemResult(1,"", 0,0 ,"", "")))
        prepareScenario(detail, episodeBySeasonModel)

        viewModel.dispatchViewAction(TvShowDetailViewAction.LoadEpisodes(id))

        Assert.assertTrue(viewModel.viewState.action.value is TvShowDetailViewState.Action.LoadEpisodes)
        Assert.assertEquals(
                episodeBySeasonModel,
                (viewModel.viewState.action.value as TvShowDetailViewState.Action.LoadEpisodes).episodes)
    }

    private fun prepareScenario(
            detail: TvShowDetailModel = TvShowDetailModel(1, "", "",
                    emptyList(), ScheduleModel("", emptyList()), ""),
            episodeBySeasonModel: EpisodeBySeasonModel = EpisodeBySeasonModel()

    ) {
        coEvery { getShowByIdUseCase(any()) } returns Resource(
                status = Status.SUCCESS,
                data = detail,
                message = null
        )

        coEvery { getEpisodeByShow(any()) } returns Resource(
                status = Status.SUCCESS,
                data = episodeBySeasonModel,
                message = null
        )
    }

}