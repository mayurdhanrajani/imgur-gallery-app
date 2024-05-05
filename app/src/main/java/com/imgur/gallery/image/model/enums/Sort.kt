package com.imgur.gallery.image.model.enums

/** This class stores the constant values of Sort which when selected will show the images from API accordingly **/
enum class Sort {

    /** This enum stores the Time value which will show the images in order of time **/
    TIME,
    /** This enum stores the Viral value which will show the images that are currently viral **/
    VIRAL,
    /** This enum stores the Top value which will show the images that are currently on top **/
    TOP;

    /** This function converts the enum value to lowercase to pass it into the API paths **/
    override fun toString(): String = this.name.lowercase()

}