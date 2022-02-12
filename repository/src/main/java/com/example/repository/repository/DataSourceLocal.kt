package com.example.repository.repository

import com.example.model.viewmodel.AppState


interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}