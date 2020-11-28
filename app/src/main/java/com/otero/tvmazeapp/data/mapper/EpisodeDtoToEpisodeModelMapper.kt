package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.EpisodeDto
import com.otero.tvmazeapp.domain.model.EpisodeModel

class EpisodeDtoToEpisodeModelMapper : BaseMapper<EpisodeDto, EpisodeModel>() {
    override fun mapFrom(from: EpisodeDto) = EpisodeModel(
            id = from.id,
            season = from.season,
            name = from.name
    )
}