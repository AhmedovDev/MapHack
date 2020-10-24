package ru.ahmedov.maphack.data.global

import ru.ahmedov.maphack.data.network.MapApi
import ru.ahmedov.maphack.data.prefs.PrefsHelper
import javax.inject.Inject
import javax.inject.Named

class DataManagerlmpl @Inject constructor(
    @Named("API_DELIVERY_APP") val api: MapApi,
    val prefsHelper: PrefsHelper
) : DataManager {}