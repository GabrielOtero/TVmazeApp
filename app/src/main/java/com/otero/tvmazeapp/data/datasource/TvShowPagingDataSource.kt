package com.otero.tvmazeapp.data.datasource

import androidx.paging.PageKeyedDataSource
import com.otero.tvmazeapp.domain.model.TvShowModel

abstract class TvShowPagingDataSource : PageKeyedDataSource<Int, TvShowModel>()