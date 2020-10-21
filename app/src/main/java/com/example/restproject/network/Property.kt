package com.example.restproject.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(
    val author: String,
    val name: String,
    @Json(name = "avatar") val avatarSrcUrl: String
):Parcelable
