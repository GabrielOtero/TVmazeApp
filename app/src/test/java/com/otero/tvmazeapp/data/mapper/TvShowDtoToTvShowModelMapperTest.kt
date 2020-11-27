package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.ImageDto
import com.otero.tvmazeapp.data.dto.TvShowDto
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowDtoToTvShowModelMapperTest {

    @Test
    fun mapFromDto_nullValues_returnModel() {
        val tvShowDto = TvShowDto(1, null, null)

        val mapper = TvShowDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowDto)

        assertEquals(tvShowDto.id, tvShowModel.id)
        assertEquals("", tvShowModel.name)
        assertEquals("", tvShowModel.image)

    }

    @Test
    fun mapFromDto_returnModel() {
        val tvShowDto = TvShowDto(1, "test", ImageDto("imageTest"))

        val mapper = TvShowDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowDto)

        assertEquals(tvShowDto.id, tvShowModel.id)
        assertEquals("test", tvShowModel.name)
        assertEquals("imageTest", tvShowModel.image)

    }
}