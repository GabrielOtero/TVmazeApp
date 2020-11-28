package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dbo.TvShowDbo
import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowDboToTvShowModelMapper : BaseMapper<TvShowDbo, TvShowModel>() {
    override fun mapFrom(from: TvShowDbo) =
        TvShowModel(
            id = from.id ?: 0,
            name = from.name ?: "",
            image = from.image ?: ""
        )

}