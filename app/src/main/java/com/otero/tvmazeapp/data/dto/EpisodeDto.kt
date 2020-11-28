package com.otero.tvmazeapp.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class EpisodeDto(
        @SerializedName("id") val id: Int,
        @SerializedName("season") val season: Int,
        @SerializedName("name") val name: String
) : Parcelable
