package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.EpisodeDto
import org.junit.Assert
import org.junit.Test

class EpisodeDtoToEpisodeModelMapperTest {

    @Test
    fun mapFromDto_nullvalues_returnModel() {
        val episodeDto = EpisodeDto(1, 1, "")

        val mapper = EpisodeDtoToEpisodeModelMapper()

        val episodeModel = mapper.mapFrom(episodeDto)

        Assert.assertEquals(episodeDto.id, episodeModel.id)
        Assert.assertEquals(episodeDto.season, episodeModel.season)
    }
}