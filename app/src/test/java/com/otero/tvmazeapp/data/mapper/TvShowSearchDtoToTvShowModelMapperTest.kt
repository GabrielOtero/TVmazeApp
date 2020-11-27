package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowSearchDtoToTvShowModelMapperTest {

    @Test
    fun mapFromDto_returnModel() {
        val tvShowSearchDto = TvShowSearchDto(score = 1.2F, show = TvShowDto(1))

        val mapper = TvShowSearchDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowSearchDto)

        assertEquals(tvShowSearchDto.show.id, tvShowModel.id)

    }
}