package com.volynkin.nasamedia.di

import com.volynkin.nasamedia.domain.usecases.AddItemToFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NASAMediaMainModule {

    @Provides
    @Singleton
    fun provideAddItemToFavoritesUseCase(): AddItemToFavoritesUseCase {
        return AddItemToFavoritesUseCase(NASAMediaItemRepository: NASAMediaItemRepository)
    }
}