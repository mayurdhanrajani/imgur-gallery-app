package com.imgur.gallery.di.networking.module

import com.imgur.gallery.BuildConfig
import com.imgur.gallery.networking.api.ApiInterceptor
import com.imgur.gallery.networking.util.NetworkingConstants.BASE_URL
import com.imgur.gallery.networking.util.NetworkingConstants.TIMEOUT
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

/** This module provides the dependencies for Networking operations **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    /** This function provides the dependency of Moshi Converter **/
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    /** This function provides the dependency of HttpLoggingInterceptor **/
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    /** This function provides the dependency of ApiInterceptor **/
    @Singleton
    @Provides
    fun provideApiInterceptor(): ApiInterceptor = ApiInterceptor()

    /** This function provides the dependency of OkHttpClient **/
    @Singleton
    @Provides
    fun provideOkHttpClient(
        apiInterceptor: ApiInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, SECONDS)
        .readTimeout(TIMEOUT, SECONDS)
        .writeTimeout(TIMEOUT, SECONDS)
        .addInterceptor(apiInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    /** This function provides the dependency of Retrofit **/
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

}