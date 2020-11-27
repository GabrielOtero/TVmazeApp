package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowSearchDtoToTvShowModelMapper : BaseMapper<TvShowSearchDto, TvShowModel>() {
    override fun mapFrom(from: TvShowSearchDto) =
        TvShowModel(
            from.show.id
        )
}
