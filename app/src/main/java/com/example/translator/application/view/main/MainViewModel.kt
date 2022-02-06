package com.example.translator.application.view.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.model.viewmodel.AppState
import com.example.translator.utils.parseOnlineSearchResults
import com.example.model.viewmodel.BaseViewModel


class MainViewModel (private val interactor: MainInteractor) : BaseViewModel<com.example.model.viewmodel.AppState>() {

    private val liveDataForViewToObserve: LiveData<com.example.model.viewmodel.AppState> = mutableLiveData

    fun subscribe(): LiveData<com.example.model.viewmodel.AppState> = liveDataForViewToObserve

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = com.example.model.viewmodel.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(com.example.model.viewmodel.AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = com.example.model.viewmodel.AppState.Success(null)
        super.onCleared()
    }
}