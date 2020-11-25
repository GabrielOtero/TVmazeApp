package com.otero.tvmazeapp.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowDto(
    @SerializedName("id") var id: Int
) : Parcelable