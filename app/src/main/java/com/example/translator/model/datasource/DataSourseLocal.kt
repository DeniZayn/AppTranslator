package com.example.translator.model.datasource

import com.example.translator.model.data.SearchResult
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> =
        remoteProvider.getData(word)
}