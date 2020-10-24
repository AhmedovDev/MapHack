package ru.ahmedov.maphack.di.global

import dagger.Module
import dagger.Provides
import ru.ahmedov.maphack.data.global.DataManager
import ru.ahmedov.maphack.data.global.GlobalData
import ru.ahmedov.maphack.data.prefs.PrefsHelper
import javax.inject.Singleton

@Module
class GlobalDataModule {

    @Provides
    @Singleton
    fun provideGlobalData(dataManager: DataManager, prefsHelper: PrefsHelper) : GlobalData =
       GlobalData(dataManager,prefsHelper)
}