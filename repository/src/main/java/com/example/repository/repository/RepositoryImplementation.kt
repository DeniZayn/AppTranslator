package com.example.repository.repository

import DataModel
import com.example.translator.model.datasource.DataSource


class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) : Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)
}