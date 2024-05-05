package com.imgur.gallery.image.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imgur.gallery.image.model.datamodel.Album
import com.imgur.gallery.image.model.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/** This ViewModel is used to observe the LiveData of all different variables based on which the UI is updated with being lifecycle aware **/
@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository) :
    ViewModel() {

    /** This variable sets the value based on the album list fetched from the API **/
    private val _albumList = MutableLiveData<PagingData<Album>>()

    /** This variable observes whether the value is changed based on the album list fetched from the API **/
    val albumList: LiveData<PagingData<Album>> = _albumList

    /** This function fetches all the albums from the API based on the text entered via user **/
    fun fetchImages(query: String) {
        viewModelScope.launch {
            imageRepository.fetchImages(query).cachedIn(viewModelScope).collectLatest {
                _albumList.value = it
            }
        }
    }

}