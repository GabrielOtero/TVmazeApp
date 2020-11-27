package com.otero.tvmazeapp.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowDetailDto(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String?,
        @SerializedName("image") val image: ImageDto?

) : Parcelable