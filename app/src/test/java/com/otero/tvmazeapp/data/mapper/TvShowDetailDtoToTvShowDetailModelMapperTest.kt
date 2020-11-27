package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.ImageDto
import com.otero.tvmazeapp.data.dto.TvShowDetailDto
import org.junit.Assert
import org.junit.Test

class TvShowDetailDtoToTvShowDetailModelMapperTest {
    @Test
    fun mapFromDto_nullvalues_returnModel() {
        val tvShowDetailDto = TvShowDetailDto(1, null, null)

        val mapper = TvShowDetailDtoToTvShowDetailModelMapper()

        val tvShowDetailModel = mapper.mapFrom(tvShowDetailDto)

        Assert.assertEquals(tvShowDetailDto.id, tvShowDetailModel.id)
        Assert.assertEquals("", tvShowDetailModel.name)
        Assert.assertEquals("", tvShowDetailModel.image)

    }

    @Test
    fun mapFromDto_returnModel() {
        val tvShowDetailDto = TvShowDetailDto(1, "test", ImageDto("test"))

        val mapper = TvShowDetailDtoToTvShowDetailModelMapper()

        val tvShowDetailModel = mapper.mapFrom(tvShowDetailDto)

        Assert.assertEquals(tvShowDetailDto.id, tvShowDetailModel.id)
        Assert.assertEquals("test", tvShowDetailModel.name)
        Assert.assertEquals(tvShowDetailDto.image?.medium, tvShowDetailModel.image)

    }
}