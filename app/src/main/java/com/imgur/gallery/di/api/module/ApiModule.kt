package com.imgur.gallery.di.api.module

import com.imgur.gallery.image.model.api.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/** This module provides the dependencies for API operations **/
@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    /** This function provides the dependency of ImageApi **/
    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi =
        retrofit.create(ImageApi::class.java)

}