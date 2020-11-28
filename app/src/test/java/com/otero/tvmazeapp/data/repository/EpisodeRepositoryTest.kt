package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.datasource.EpisodeRemoteDataSource
import com.otero.tvmazeapp.domain.model.EpisodeModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class EpisodeRepositoryTest {

    private val episodeRemoteDataSource = mockk<EpisodeRemoteDataSource>()
    private val repository = EpisodeRepositoryImpl(episodeRemoteDataSource)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()

        repository.getTvEpisodesByShowId(id)

        coVerify(exactly = 1) { episodeRemoteDataSource.getTvEpisodesByShowId(id) }
    }


    private fun prepareScenario(list: List<EpisodeModel> = listOf(EpisodeModel(1, 1, ""))) {
        coEvery { episodeRemoteDataSource.getTvEpisodesByShowId(any()) } returns Resource(
                status = Status.SUCCESS,
                data = list,
                message = null
        )
    }
}