package com.otero.tvmazeapp.data

import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows")
    suspend fun getShowsByPage(@Query("page") page: Int = 0): List<TvShowDto>

    @GET("search/shows")
    suspend fun getTvShowsByText(@Query("q") query: String): List<TvShowSearchDto>
}
