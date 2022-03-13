package com.volynkin.nasamedia.ui

import androidx.lifecycle.ViewModel
import com.volynkin.nasamedia.domain.usecases.AddItemToFavoritesUseCase
import com.volynkin.nasamedia.domain.usecases.GetFavoritesNasaMediaItemsUseCase
import com.volynkin.nasamedia.domain.usecases.GetNasaMediaItemsUseCase
import com.volynkin.nasamedia.domain.usecases.RemoveItemToFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NASAMediaItemsViewModel @Inject constructor(
    private val addItemToFavoritesUseCase: AddItemToFavoritesUseCase,
    private val getNasaMediaItemsUseCase: GetNasaMediaItemsUseCase,
    private val removeItemToFavoritesUseCase: RemoveItemToFavoritesUseCase,
    private val getFavoritesNasaMediaItemsUseCase: GetFavoritesNasaMediaItemsUseCase
) : ViewModel() {

    companion object {
        const val TAG = "NASAMediaItemsViewModel"
    }


}