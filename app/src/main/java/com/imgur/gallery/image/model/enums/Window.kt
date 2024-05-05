package com.imgur.gallery.image.model.enums

/** This class stores the constant values of Window which when selected will show the images from API accordingly **/
enum class Window {

    /** This enum stores the Day value which will show the images of that day **/
    DAY,
    /** This enum stores the Day value which will show the images of that week **/
    WEEK,
    /** This enum stores the Day value which will show the images of that month **/
    MONTH,
    /** This enum stores the Day value which will show the images of that year **/
    YEAR,
    /** This enum stores the Day value which will show the images of all time **/
    ALL;

    /** This function converts the enum value to lowercase to pass it into the API paths **/
    override fun toString(): String = this.name.lowercase()

}