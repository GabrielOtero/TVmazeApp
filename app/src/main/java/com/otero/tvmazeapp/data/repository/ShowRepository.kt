package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.ShowModel

interface ShowRepository {
    suspend fun getShowsByPage(page: Int = 1): Resource<List<ShowModel>>
}