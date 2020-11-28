package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.EpisodeDto
import com.otero.tvmazeapp.data.dto.ImageDto
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class EpisodeDtoToEpisodeModelMapperTest {

    @Test
    fun mapFromDto_nullvalues_returnModel() {
        val episodeDto = EpisodeDto(1, 1, "", 0, "", ImageDto(""))

        val mapper = EpisodeDtoToEpisodeModelMapper()

        val episodeModel = mapper.mapFrom(episodeDto)

        assertEquals(episodeDto.id, episodeModel.id)
        assertEquals(episodeDto.season, episodeModel.season)
        assertEquals(episodeDto.name, episodeModel.name)
        assertEquals(episodeDto.number, episodeModel.number)
        assertEquals(episodeDto.summary, episodeModel.summary)
        assertEquals(episodeDto.image?.medium, episodeModel.image)
    }
}