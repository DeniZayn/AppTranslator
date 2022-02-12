package com.example.translator.application.view.main

import com.example.core.viewmodel.Interactor
import com.example.model.viewmodel.AppState
import com.example.model.viewmodel.dto.SearchResultDto
import com.example.repository.repository.Repository
import com.example.repository.repository.RepositoryLocal
import com.example.translator.utils.mapSearchResultToResult


class MainInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}

