package com.imgur.gallery.networking.api

import com.imgur.gallery.networking.util.NetworkingConstants.AUTHORIZATION_HEADER_NAME
import com.imgur.gallery.networking.util.NetworkingConstants.AUTHORIZATION_HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response

/** This class is used to make changes in the request before API is called **/
class ApiInterceptor : Interceptor {

    /** This function will be called to add Authorization values in the request before API is called **/
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .header(
                AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE
            ).build()

        return chain.proceed(request)
    }

}