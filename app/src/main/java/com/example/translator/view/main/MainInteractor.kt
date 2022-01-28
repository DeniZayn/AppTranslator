package com.example.translator.view.main

import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.repository.Repository
import com.example.translator.viewmodel.Interactor



class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<DataModel> {
    // Добавляем suspend
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            DataModel {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}

