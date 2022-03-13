package com.volynkin.nasamedia.di

import android.content.Context
import com.volynkin.nasamedia.data.api.NASAMediaService
import com.volynkin.nasamedia.data.db.NASAMediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NASAMediaNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HttpLogging

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").i(message)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideNASAMediaDatabase(@ApplicationContext context: Context): NASAMediaDatabase {
        return NASAMediaDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideNASAMediaService(client: OkHttpClient): NASAMediaService {
        return Retrofit.Builder().client(client)
            .baseUrl("https://images-api.nasa.gov")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NASAMediaService::class.java)
    }
}