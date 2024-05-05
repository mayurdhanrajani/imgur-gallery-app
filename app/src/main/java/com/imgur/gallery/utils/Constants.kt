package com.imgur.gallery.utils

/** This object stores the constant values used in the project **/
object Constants {

    /** This variable stores the starting page index of paging data from API **/
    const val STARTING_PAGE_INDEX = 0

    /** This variable stores the value by which closes page position to anchor position should be incremented/decremented **/
    const val INCREMENT_POSITION = 1

    /** This variable stores number by which next page should be incremented **/
    const val INCREMENT_PAGE_NUMBER = 1

    /** This variable stores the empty String value **/
    const val EMPTY_STRING = ""

    /** This variable stores the default Int value **/
    const val DEFAULT_INT_VALUE = 0

    /** This variable stores the default Long value **/
    const val DEFAULT_LONG_VALUE = 0L

    /** This variable stores the default Boolean value **/
    const val DEFAULT_BOOLEAN_VALUE = false

    /** This variable stores the date format in which it will be shown in image cell **/
    const val DATE_FORMAT = "dd/MM/yy hh:mm a"

    /** This variable stores the value to convert from epoch time to milliseconds **/
    const val EPOCH_TO_MILLISECONDS_CONVERTER = 1000

    /** This variable stores the span count which grid layout should be using **/
    const val SPAN_COUNT = 3

    /** This variable stores the text size of SearchView **/
    const val SEARCH_VIEW_TEXT_SIZE = 18.0F

    /** This variable stores the page size of API images to be fetched **/
    const val PAGE_SIZE = 100

    /** This variable stores the dot value which is used to separate the URL **/
    const val DOT_SEPARATOR = "."

    /** This variable stores the path value which is used to separate the URL **/
    const val PATH_SEPARATOR = "/"

    /** This variable stores the dot index value which is used to separate the URL **/
    const val DOT_SELECTOR_INDEX = 2

    /** This variable stores the path index value which is used to separate the URL **/
    const val PATH_SELECTOR_INDEX = 1

    /** This variable stores the thumbnail suffix value to fetch small thumbnail image from API **/
    const val SMALL_THUMBNAIL_SUFFIX = "t"

    /** This variable stores the thumbnail suffix value to fetch large thumbnail image from API **/
    const val LARGE_THUMBNAIL_SUFFIX = "l"

    /** This variable stores the stroke width value of CircularProgressDrawable **/
    const val CIRCULAR_PROGRESS_STROKE_WIDTH = 5f

    /** This variable stores the center radius value of CircularProgressDrawable **/
    const val CIRCULAR_PROGRESS_CENTER_RADIUS = 30f

}