package com.example.translator.model.datasource

import com.example.translator.model.data.SearchResult
import io.reactivex.Observable


class RoomDataBaseImplementation : DataSource<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> {
        TODO("not implemented")
    }
}