package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.TvShowDetailDto
import com.otero.tvmazeapp.domain.model.ScheduleModel
import com.otero.tvmazeapp.domain.model.TvShowDetailModel

class TvShowDetailDtoToTvShowDetailModelMapper : BaseMapper<TvShowDetailDto, TvShowDetailModel>() {
    override fun mapFrom(from: TvShowDetailDto) =
            TvShowDetailModel(
                    id = from.id,
                    name = from.name ?: "",
                    image = from.image?.medium ?: "",
                    genres = from.genres ?: emptyList(),
                    summary = from.summary ?: "",
                    schedule = ScheduleModel(
                            time = from.schedule?.time ?: "",
                            days = from.schedule?.days ?: emptyList()
                    )
            )
}