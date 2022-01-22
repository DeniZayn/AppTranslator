package com.example.translator.view.base

import com.example.translator.model.data.DataModel


interface View {
    fun renderData(dataModel: DataModel)
}