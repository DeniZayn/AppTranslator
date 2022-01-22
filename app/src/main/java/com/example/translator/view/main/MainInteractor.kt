package com.example.translator.view.main


import android.util.Log
import com.example.translator.model.data.DataModel
import com.example.translator.model.data.SearchResult
import com.example.translator.model.repository.Repository
import com.example.translator.presenter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<SearchResult>>,
    private val localRepository: Repository<List<SearchResult>>
) : Interactor<DataModel> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<DataModel> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { list: List<SearchResult> ->
                list.forEach {
                    Log.d("RESULT", "${it.text} ~ " +
                            "${it.meanings?.get(0)?.translation?.text} ~ " +
                            "${it.meanings?.get(0)?.imageUrl}")
                }
                DataModel.Success(list)
            }
        } else {
            localRepository.getData(word).map {
                Log.d("RESULT", "${it[0].text}")
                DataModel.Success(it) }
        }
    }
}
