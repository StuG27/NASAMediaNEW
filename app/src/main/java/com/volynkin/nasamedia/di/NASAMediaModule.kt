package com.volynkin.nasamedia.di

import android.content.Context
import android.util.Log
import com.volynkin.nasamedia.data.api.NASAMediaItemsResponseMapper
import com.volynkin.nasamedia.data.api.NASAMediaService
import com.volynkin.nasamedia.data.db.NASAMediaDatabase
import com.volynkin.nasamedia.data.entities.NASAMediaItemsEntityMapper
import com.volynkin.nasamedia.data.repositories.*
import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository
import com.volynkin.nasamedia.domain.usecases.AddItemToFavoritesUseCase
import com.volynkin.nasamedia.domain.usecases.GetFavoritesNasaMediaItemsUseCase
import com.volynkin.nasamedia.domain.usecases.GetNasaMediaItemsUseCase
import com.volynkin.nasamedia.domain.usecases.RemoveItemFromFavoritesUseCase
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
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NASAMediaModule {

    @Provides
    @Singleton
    fun provideNASAMediaItemsRemoteDataSource(
        service: NASAMediaService,
        responseMapper: NASAMediaItemsResponseMapper
    ): NASAMediaItemsRemoteDataSource {
        return NASAMediaItemsRemoteDataSourceImpl(service, responseMapper)
    }

    @Provides
    @Singleton
    fun provideNASAMediaItemsLocalDataSource(
        database: NASAMediaDatabase,
        entityMapper: NASAMediaItemsEntityMapper
    ): NASAMediaItemsLocalDataSource {
        return NASAMediaItemsLocalDataSourceImpl(database, entityMapper)
    }

    @Provides
    @Singleton
    fun provideNASAMediaItemsEntityMapper(): NASAMediaItemsEntityMapper {
        return NASAMediaItemsEntityMapper()
    }

    @Provides
    @Singleton
    fun provideNASAMediaItemsResponseMapper(): NASAMediaItemsResponseMapper {
        return NASAMediaItemsResponseMapper()
    }

    @Provides
    @Singleton
    fun provideAddItemToFavoritesUseCase(repository: NASAMediaItemsRepository): AddItemToFavoritesUseCase {
        return AddItemToFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoritesNasaMediaItemsUseCase(repository: NASAMediaItemsRepository): GetFavoritesNasaMediaItemsUseCase {
        return GetFavoritesNasaMediaItemsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetNasaMediaItemsUseCase(repository: NASAMediaItemsRepository): GetNasaMediaItemsUseCase {
        return GetNasaMediaItemsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRemoveItemFromFavoritesUseCase(repository: NASAMediaItemsRepository): RemoveItemFromFavoritesUseCase {
        return RemoveItemFromFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideNASAMediaItemsRepository(
        localDataSource: NASAMediaItemsLocalDataSource,
        remoteDataSource: NASAMediaItemsRemoteDataSource
    ): NASAMediaItemsRepository {
        return NASAMediaItemsRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.e("httpLoggingInterceptor", message)
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