package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowSearchDtoToTvShowModelMapper : BaseMapper<TvShowSearchDto, TvShowModel>() {
    override fun mapFrom(from: TvShowSearchDto) =
        TvShowModel(
            id = from.show.id,
            name = from.show.name ?: "",
            image = from.show.image?.medium ?: ""
        )
}
