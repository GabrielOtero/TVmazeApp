package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.ImageDto
import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowSearchDtoToTvShowModelMapperTest {

    @Test
    fun mapFromDto_nullValues_returnModel() {
        val tvShowSearchDto = TvShowSearchDto(score = 1.2F, show = TvShowDto(1, null, null))

        val mapper = TvShowSearchDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowSearchDto)

        assertEquals(tvShowSearchDto.show.id, tvShowModel.id)
        assertEquals("", tvShowModel.name)
        assertEquals("", tvShowModel.image)

    }

    @Test
    fun mapFromDto_returnModel() {
        val tvShowSearchDto = TvShowSearchDto(score = 1.2F, show = TvShowDto(1, "test", ImageDto("imageMedium")))

        val mapper = TvShowSearchDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowSearchDto)

        assertEquals(tvShowSearchDto.show.id, tvShowModel.id)
        assertEquals("test", tvShowModel.name)
        assertEquals("imageMedium", tvShowModel.image)

    }
}