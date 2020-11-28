package com.otero.tvmazeapp.domain.model

open class EpisodeItemResult(val id: Int = 0,
                             val name: String,
                             val resultType: ResultType = ResultType.EPISODE)

class SeasonHeader(val number: Int) :  EpisodeItemResult(0, "", ResultType.SEASON_HEADER)

