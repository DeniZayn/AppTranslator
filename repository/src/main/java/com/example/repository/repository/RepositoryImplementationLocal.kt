package com.example.repository.repository

import com.example.model.viewmodel.AppState
import DataModel
import com.example.translator.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: com.example.model.viewmodel.AppState) {
        dataSource.saveToDB(appState)
    }
}