package com.imgur.gallery.networking.api

import com.squareup.moshi.JsonClass

/** This data class is used to wrap the API response **/
@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    /** This variable is used to store the data received from API when it is a success **/
    var data: List<T>? = listOf()
)