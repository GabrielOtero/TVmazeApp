package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.TvShowDto
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowDtoToTvShowModelMapperTest {

    @Test
    fun mapFromDto_returnModel() {
        val tvShowDto = TvShowDto(1)

        val mapper = TvShowDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowDto)

        assertEquals(tvShowDto.id, tvShowModel.id)

    }
}