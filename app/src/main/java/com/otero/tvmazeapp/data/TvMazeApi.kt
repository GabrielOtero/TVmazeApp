package com.otero.tvmazeapp.data

import com.otero.tvmazeapp.data.dto.EpisodeDto
import com.otero.tvmazeapp.data.dto.TvShowDetailDto
import com.otero.tvmazeapp.data.dto.TvShowDto
import com.otero.tvmazeapp.data.dto.TvShowSearchDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows")
    suspend fun getShowsByPage(@Query("page") page: Int = 0): List<TvShowDto>

    @GET("search/shows")
    suspend fun getTvShowsByText(@Query("q") query: String): List<TvShowSearchDto>

    @GET("shows/{id}")
    suspend fun getTvShowById(@Path("id") id: Int): TvShowDetailDto

    @GET("shows/{id}/episodes")
    suspend fun getTvEpisodesByShowId(@Path("id") id: Int): List<EpisodeDto>

    @GET("shows")
    fun getShowsByPageRx(@Query("page") page: Int = 0) : Observable<List<TvShowDto>>

}
