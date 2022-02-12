package com.example.repository.repository.api

import com.example.model.viewmodel.userdata.DataModel
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.Deferred

interface ApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}