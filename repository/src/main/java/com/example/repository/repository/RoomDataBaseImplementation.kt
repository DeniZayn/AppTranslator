package com.example.repository.repository

import com.example.repository.repository.room.HistoryDao
import com.example.translator.utils.convertDataModelSuccessToEntity
import com.example.translator.utils.mapHistoryEntityToSearchResult


class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: com.example.model.viewmodel.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}