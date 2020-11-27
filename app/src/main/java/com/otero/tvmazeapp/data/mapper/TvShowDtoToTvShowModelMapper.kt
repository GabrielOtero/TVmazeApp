package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowDtoToTvShowModelMapper : BaseMapper<TvShowDto, TvShowModel>() {
    override fun mapFrom(from: TvShowDto) =
        TvShowModel(
            id = from.id,
            name = from.name ?: "",
            image = from.image?.medium ?: ""
        )

}