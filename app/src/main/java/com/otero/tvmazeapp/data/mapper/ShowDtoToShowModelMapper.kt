package com.otero.tvmazeapp.data.mapper

import com.otero.tvmazeapp.data.dto.ShowDto
import com.otero.tvmazeapp.domain.model.ShowModel

class ShowDtoToShowModelMapper : BaseMapper<ShowDto, ShowModel>() {
    override fun mapFrom(from: ShowDto) =
        ShowModel(
            from.id
        )

}