package com.example.historyscreen.view.history

import com.example.core.viewmodel.Interactor
import com.example.model.viewmodel.AppState
import com.example.model.viewmodel.dto.SearchResultDto
import com.example.repository.repository.Repository
import com.example.repository.repository.RepositoryLocal
import com.example.translator.utils.mapSearchResultToResult


class HistoryInteractor(

    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
    ) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}
