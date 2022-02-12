package com.example.repository.repository

import com.example.model.viewmodel.AppState
import com.example.model.viewmodel.dto.SearchResultDto
import com.example.repository.repository.room.HistoryDao
import com.example.repository.repository.room.convertDataModelSuccessToEntity
import com.example.repository.repository.room.mapHistoryEntityToSearchResult


class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}