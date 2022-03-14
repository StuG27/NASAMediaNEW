package com.volynkin.nasamedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.usecases.AddItemToFavoritesUseCase
import com.volynkin.nasamedia.domain.usecases.GetFavoritesNasaMediaItemsUseCase
import com.volynkin.nasamedia.domain.usecases.GetNasaMediaItemsUseCase
import com.volynkin.nasamedia.domain.usecases.RemoveItemFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NASAMediaItemsViewModel @Inject constructor(
    private val addItemToFavoritesUseCase: AddItemToFavoritesUseCase,
    private val getNasaMediaItemsUseCase: GetNasaMediaItemsUseCase,
    private val removeItemFromFavoritesUseCase: RemoveItemFromFavoritesUseCase,
    private val getFavoritesNasaMediaItemsUseCase: GetFavoritesNasaMediaItemsUseCase
) : ViewModel() {

    companion object {
        const val TAG = "NASAMediaItemsViewModel"
    }

    private val localDataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = localDataLoading

    private val localItems = MutableLiveData<List<BookWithStatus>>()
    val items = localItems

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteItems = arrayListOf<NASAMediaItem>()

    fun getNasaMediaItems(keyWord: String) {
        Timber.tag(TAG).e("getNasaMediaItems")
        viewModelScope.launch {
            localDataLoading.postValue(true)
            val itemsResult = getNasaMediaItemsUseCase.invoke(keyWord)
            if (itemsResult.isSuccess) {
                _remoteItems.clear()
                _remoteItems.addAll(itemsResult.getOrThrow())
            } else {
                localDataLoading.postValue(false)
                items.value = emptyList()
                _error.postValue(itemsResult.exceptionOrNull()?.message)
            }
        }
    }

    suspend fun addItemToFavorites(item: NASAMediaItem) {
        Timber.tag(TAG).e("addItemToFavorites")
        addItemToFavoritesUseCase.invoke(item)
    }

    suspend fun removeItemFromFavorites(item: NASAMediaItem) {
        Timber.tag(TAG).e("removeItemToFavorites")
        viewModelScope.launch {
            removeItemFromFavoritesUseCase.invoke(item)
        }
    }

    suspend fun getFavoritesNasaMediaItems() {
        Timber.tag(TAG).e("getFavoritesNasaMediaItems")
        viewModelScope.launch {
            getFavoritesNasaMediaItemsUseCase.invoke()
        }
    }
}