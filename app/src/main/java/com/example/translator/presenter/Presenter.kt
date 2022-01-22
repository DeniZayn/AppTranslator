package com.example.translator.presenter

import android.view.View
import com.example.translator.model.data.DataModel

interface Presenter<T : DataModel, V: View> {
    fun attachView(view: V)
    fun detachView(view:V)
    fun getData(word: String, isOnline: Boolean)

}