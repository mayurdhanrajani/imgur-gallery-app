package com.imgur.gallery.image.model.api

import com.imgur.gallery.image.model.datamodel.Album
import com.imgur.gallery.networking.api.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/** This interface is used to create APIs for performing networking operations using Retrofit **/
interface ImageApi {

    /** This API is used to fetch all the images based on the parameters passed in the query and path**/
    @GET("3/gallery/search/{sort}/{window}/{page}")
    suspend fun fetchImages(
        @Path("sort") sort: String,
        @Path("window") window: String,
        @Path("page") page: Int,
        @Query("q") query: String
    ): Response<ApiResponse<Album>>

}
