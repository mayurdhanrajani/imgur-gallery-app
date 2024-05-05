package com.imgur.gallery.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.imgur.gallery.utils.Constants.CIRCULAR_PROGRESS_CENTER_RADIUS
import com.imgur.gallery.utils.Constants.CIRCULAR_PROGRESS_STROKE_WIDTH
import com.imgur.gallery.utils.Constants.DATE_FORMAT
import com.imgur.gallery.utils.Constants.DOT_SELECTOR_INDEX
import com.imgur.gallery.utils.Constants.DOT_SEPARATOR
import com.imgur.gallery.utils.Constants.EPOCH_TO_MILLISECONDS_CONVERTER
import com.imgur.gallery.utils.Constants.LARGE_THUMBNAIL_SUFFIX
import com.imgur.gallery.utils.Constants.PATH_SELECTOR_INDEX
import com.imgur.gallery.utils.Constants.PATH_SEPARATOR
import com.imgur.gallery.utils.Constants.SMALL_THUMBNAIL_SUFFIX
import java.text.SimpleDateFormat
import java.util.*

/** This file stores the extension functions to perform some operations on existing classes **/

/** This function converts the TimeStamp to Date into Local TimeZone **/
fun Long.toDate(): String {
    val parser = SimpleDateFormat(DATE_FORMAT, Locale.US)
    parser.timeZone = TimeZone.getDefault()
    return parser.format(Date(this.times(EPOCH_TO_MILLISECONDS_CONVERTER)))
}

/** This function converts the image link to small thumbnail for list view **/
fun String.toListThumbNailLink(): String {
    val imageName =
        this.split(DOT_SEPARATOR)[DOT_SELECTOR_INDEX].split(PATH_SEPARATOR)[PATH_SELECTOR_INDEX]
    return this.replace(imageName, "$imageName$SMALL_THUMBNAIL_SUFFIX")
}

/** This function converts the image link to large thumbnail for grid view **/
fun String.toGridThumbNailLink(): String {
    val imageName =
        this.split(DOT_SEPARATOR)[DOT_SELECTOR_INDEX].split(PATH_SEPARATOR)[PATH_SELECTOR_INDEX]
    return this.replace(imageName, "$imageName$LARGE_THUMBNAIL_SUFFIX")
}

/** This function displays the CircularProgressDrawable as a placeholder until image is loaded **/
fun Context.imagePlaceHolder(): Drawable = CircularProgressDrawable(this).apply {
    strokeWidth = CIRCULAR_PROGRESS_STROKE_WIDTH
    centerRadius = CIRCULAR_PROGRESS_CENTER_RADIUS
    start()
}