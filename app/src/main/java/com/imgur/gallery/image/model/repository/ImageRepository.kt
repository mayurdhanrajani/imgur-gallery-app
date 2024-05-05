package com.imgur.gallery.image.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imgur.gallery.image.model.api.ImageApi
import com.imgur.gallery.image.model.datamodel.Album
import com.imgur.gallery.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/** This repository fetch the images data from API to show it to users **/
@Singleton
class ImageRepository @Inject constructor(private val imageApi: ImageApi) {

    /** This function fetches the images data from API using pagination **/
    fun fetchImages(query: String): Flow<PagingData<Album>> =
        Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { ImagePagingDataSource(imageApi, query) }
        ).flow

}
