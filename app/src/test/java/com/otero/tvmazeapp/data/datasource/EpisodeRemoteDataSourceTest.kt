package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.dto.EpisodeDto
import com.otero.tvmazeapp.data.dto.ImageDto
import com.otero.tvmazeapp.data.mapper.EpisodeDtoToEpisodeModelMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class EpisodeRemoteDataSourceTest {
    private val api = mockk<TvMazeApi>()

    private val episodeRemoteDataSourceImpl =
            EpisodeRemoteDataSourceImpl(
                    api,
                    ResponseHandler(),
                    EpisodeDtoToEpisodeModelMapper()
            )

    @Test
    fun getShowsByPage_returnSucessResult() = runBlocking {
        prepareScenario(listOf(EpisodeDto(1, 1, "", 0, "", ImageDto(""))))

        episodeRemoteDataSourceImpl.getTvEpisodesByShowId(1)

        coVerify(exactly = 1) { api.getTvEpisodesByShowId(1) }
    }


    private fun prepareScenario(list: List<EpisodeDto> = emptyList()) {
        coEvery { api.getTvEpisodesByShowId(any()) } returns list
    }
}