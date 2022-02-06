package com.example.repository.repository

interface DataSource<T> {
    suspend fun getData(word: String): T
}