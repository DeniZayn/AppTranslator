package com.example.historyscreen.view.history

import androidx.lifecycle.LiveData
import com.example.model.viewmodel.AppState
import com.example.translator.utils.parseLocalSearchResults
import com.example.model.viewmodel.BaseViewModel

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<com.example.model.viewmodel.AppState>() {

    private val liveDataForViewToObserve: LiveData<com.example.model.viewmodel.AppState> = mutableLiveData

    fun subscribe(): LiveData<com.example.model.viewmodel.AppState> = liveDataForViewToObserve

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = com.example.model.viewmodel.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(com.example.model.viewmodel.AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = com.example.model.viewmodel.AppState.Success(null)
        super.onCleared()
    }
}