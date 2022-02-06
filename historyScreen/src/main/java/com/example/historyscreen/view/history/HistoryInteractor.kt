package com.example.historyscreen.view.history

import com.example.model.viewmodel.AppState
import DataModel
import com.example.repository.repository.Repository
import com.example.repository.repository.RepositoryLocal
import com.example.model.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<com.example.model.viewmodel.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.viewmodel.AppState {
        return com.example.model.viewmodel.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}