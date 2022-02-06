package com.example.translator.utils



fun parseOnlineSearchResults(state: com.example.model.viewmodel.AppState): com.example.model.viewmodel.AppState = com.example.model.viewmodel.AppState.Success(mapResult(state, true))

fun parseLocalSearchResults(data: com.example.model.viewmodel.AppState): com.example.model.viewmodel.AppState = com.example.model.viewmodel.AppState.Success(mapResult(data, false))

private fun mapResult(data: com.example.model.viewmodel.AppState, isOnline: Boolean): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is com.example.model.viewmodel.AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(data: com.example.model.viewmodel.AppState.Success, isOnline: Boolean, newDataModels: ArrayList<DataModel>) {
    val dataModels: List<DataModel> = data.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<com.example.model.viewmodel.Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(
                    com.example.model.viewmodel.Meanings(
                        meaning.translation,
                        meaning.imageUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun mapHistoryEntityToSearchResult(list: List<com.example.repository.repository.room.HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: com.example.model.viewmodel.AppState): com.example.repository.repository.room.HistoryEntity? {
    return when (appState) {
        is com.example.model.viewmodel.AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                com.example.repository.repository.room.HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}

fun convertMeaningsToString(meanings: List<com.example.model.viewmodel.Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.text, ", ")
        } else {
            meaning.translation?.text
        }
    }
    return meaningsSeparatedByComma
}