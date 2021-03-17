package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.dto.ImageDto
import com.otero.tvmazeapp.data.dto.TvShowDetailDto
import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import com.otero.tvmazeapp.data.mapper.TvShowDetailDtoToTvShowDetailModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowSearchDtoToTvShowModelMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TvShowRemoteDataSourceTest {
    private val api = mockk<TvMazeApi>()

    private val tvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(
                api,
                ResponseHandler(),
                TvShowDtoToTvShowModelMapper(),
                TvShowSearchDtoToTvShowModelMapper(),
                TvShowDetailDtoToTvShowDetailModelMapper()
        )

    @Test
    fun getShowsByPage_returnSucessResult() = runBlocking {
        prepareScenario(listOf(TvShowDto(1, null, null)))

        val tvShowPagingDataSource = tvShowRemoteDataSource.getPagedShows()
        assertNotNull(tvShowPagingDataSource)

    }

    @Test
    fun getShowsByText_returnSucessResult() = runBlocking {
        prepareScenario(listOf(TvShowDto(1, null, null)))
        val searchText = "text"

        tvShowRemoteDataSource.getTvShowsByText(searchText)
        coVerify(exactly = 1) { api.getTvShowsByText(searchText) }
    }

    @Test
    fun getShowsById_returnSucessResult() = runBlocking {
        prepareScenario()
        val id = 1

        tvShowRemoteDataSource.getTvShowById(id)
        coVerify(exactly = 1) { api.getTvShowById(id) }
    }


    private fun prepareScenario(list: List<TvShowDto> = emptyList(),
                                listSearch: List<TvShowSearchDto> = emptyList(),
                                tvShowDetailDto: TvShowDetailDto = TvShowDetailDto(1, "",
                                        ImageDto(""), null, null,
                                        null)
    ) {
        coEvery { api.getShowsByPage(any()) } returns list
        coEvery { api.getTvShowsByText(any()) } returns listSearch
        coEvery { api.getTvShowById(any()) } returns tvShowDetailDto
    }
}