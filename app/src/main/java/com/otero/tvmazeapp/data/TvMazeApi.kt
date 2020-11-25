package com.otero.tvmazeapp.data

import com.otero.tvmazeapp.data.dto.ShowDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows")
    suspend fun getShowsByPage(@Query("page") page: Int = 1): List<ShowDto>
}
