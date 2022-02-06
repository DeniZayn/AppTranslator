package com.example.translator.application.view.main

import com.example.model.viewmodel.AppState
import DataModel
import com.example.repository.repository.Repository
import com.example.repository.repository.RepositoryLocal
import com.example.model.viewmodel.Interactor



class MainInteractor (
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<com.example.model.viewmodel.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.viewmodel.AppState {
        val appState: com.example.model.viewmodel.AppState
        if (fromRemoteSource) {
            appState = com.example.model.viewmodel.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = com.example.model.viewmodel.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}

