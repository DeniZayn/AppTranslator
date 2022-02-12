package com.example.translator.di

import androidx.room.Room
import com.example.historyscreen.view.history.HistoryActivity
import com.example.historyscreen.view.history.HistoryInteractor
import com.example.historyscreen.view.history.HistoryViewModel
import com.example.model.viewmodel.dto.SearchResultDto
import com.example.translator.application.view.main.MainInteractor
import com.example.translator.application.view.main.MainViewModel
import com.example.translator.application.view.main.MainActivity
import com.example.repository.*
import com.example.repository.repository.room.HistoryDataBase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<SearchResultDto>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<SearchResultDto>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}
