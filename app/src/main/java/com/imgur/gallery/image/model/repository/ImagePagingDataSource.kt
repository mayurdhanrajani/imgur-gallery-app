package com.imgur.gallery.image.model.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imgur.gallery.image.model.api.ImageApi
import com.imgur.gallery.image.model.datamodel.Album
import com.imgur.gallery.image.model.enums.Sort
import com.imgur.gallery.image.model.enums.Window
import com.imgur.gallery.utils.Constants.INCREMENT_PAGE_NUMBER
import com.imgur.gallery.utils.Constants.INCREMENT_POSITION
import com.imgur.gallery.utils.Constants.STARTING_PAGE_INDEX

/** This data sources provides the images data from API using pagination **/
class ImagePagingDataSource(
    private val imageApi: ImageApi,
    private val query: String
) : PagingSource<Int, Album>() {

    /** This function fetches the images data from API using pagination with the help of previous and next keys using numbers **/
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> = try {
        val currentPageKey = params.key ?: STARTING_PAGE_INDEX

        val apiResponse = imageApi.fetchImages(
            Sort.TOP.toString(),
            Window.WEEK.toString(),
            currentPageKey,
            query
        )

        val responseBody = apiResponse.body()

        val data = responseBody?.data.orEmpty()

        val filteredData =
            data.filter { it.images.isNotEmpty() && it.images[0].animated }

        LoadResult.Page(
            data = filteredData,
            prevKey = null,
            nextKey = if (filteredData.isEmpty()) null else currentPageKey.plus(
                INCREMENT_PAGE_NUMBER
            )
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    /** This function provides the refresh key which is closest to the anchor position **/
    override fun getRefreshKey(state: PagingState<Int, Album>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(INCREMENT_POSITION)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(INCREMENT_POSITION)
        }

}