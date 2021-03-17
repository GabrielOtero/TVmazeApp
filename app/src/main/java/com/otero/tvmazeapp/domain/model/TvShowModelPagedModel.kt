package com.otero.tvmazeapp.domain.model

data class PagedTvShowListModel(val list: List<TvShowModel> = emptyList(), val hasMore : Boolean = false)