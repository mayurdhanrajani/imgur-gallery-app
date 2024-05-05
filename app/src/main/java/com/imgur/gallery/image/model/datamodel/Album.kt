package com.imgur.gallery.image.model.datamodel

import com.imgur.gallery.utils.Constants.DEFAULT_INT_VALUE
import com.imgur.gallery.utils.Constants.EMPTY_STRING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** This data class is used to store the Album info **/
@JsonClass(generateAdapter = true)
data class Album(
    /** This variable is used to store the id to differentiate other albums in the list **/
    var id: String = EMPTY_STRING,
    /** This variable is used to store the title value of the album **/
    var title: String = EMPTY_STRING,
    @Json(name = "images_count")
    /** This variable is used to store the count of images in that album **/
    var imagesCount: Int = DEFAULT_INT_VALUE,
    /** This variable is used to store the list of images that will be shown to the user **/
    var images: List<Image> = listOf()
)