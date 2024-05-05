package com.imgur.gallery.image.model.datamodel

import com.imgur.gallery.utils.Constants.DEFAULT_BOOLEAN_VALUE
import com.imgur.gallery.utils.Constants.DEFAULT_LONG_VALUE
import com.imgur.gallery.utils.Constants.EMPTY_STRING
import com.squareup.moshi.JsonClass

/** This data class is used to store the Image info **/
@JsonClass(generateAdapter = true)
data class Image(
    /** This variable is used to store the datetime value when the image was uploaded **/
    var datetime: Long = DEFAULT_LONG_VALUE,
    /** This variable is used to store whether the image is static or animated **/
    var animated: Boolean = DEFAULT_BOOLEAN_VALUE,
    /** This variable is used to store the link which will be used to show to the user **/
    var link: String = EMPTY_STRING
)