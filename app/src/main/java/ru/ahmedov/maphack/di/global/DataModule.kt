package ru.ahmedov.deliveryapp.di.global.modules

import dagger.Binds
import dagger.Module
import ru.ahmedov.maphack.data.global.DataManager
import ru.ahmedov.maphack.data.global.DataManagerlmpl

import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun provideDataManager(dataManager: DataManagerlmpl) : DataManager
}