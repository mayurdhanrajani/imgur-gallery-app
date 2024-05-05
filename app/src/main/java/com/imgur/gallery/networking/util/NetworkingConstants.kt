package com.imgur.gallery.networking.util

/** This object stores the constant values used for networking **/
object NetworkingConstants {

    /** This variable stores the API timeout value for connecting, reading and writing with the APIs **/
    const val TIMEOUT = 30L

    /** This variable stores the Base URL at which API endpoints are appended using Retrofit **/
    const val BASE_URL = "https://api.imgur.com/"

    /** This variable stores the Authorization Header name **/
    const val AUTHORIZATION_HEADER_NAME = "Authorization"

    /** This variable stores the Authorization Header value **/
    const val AUTHORIZATION_HEADER_VALUE = "Client-ID 6e0507d36e5f691"

}