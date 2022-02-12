package com.example.repository.repository

import com.example.model.viewmodel.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}