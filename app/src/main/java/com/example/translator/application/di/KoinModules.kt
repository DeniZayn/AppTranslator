package com.example.translator.di

import com.example.historyscreen.view.history.HistoryInteractor
import com.example.historyscreen.view.history.HistoryViewModel
import com.example.translator.application.view.main.MainInteractor
import com.example.translator.application.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), com.example.repository.repository.room.HistoryDataBase::class.java, "HistoryDBase").build() }
    single { get<com.example.repository.repository.room.HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(com.example.repository.repository.RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(
        com.example.repository.repository.RoomDataBaseImplementation(
            get()
        )
    )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}