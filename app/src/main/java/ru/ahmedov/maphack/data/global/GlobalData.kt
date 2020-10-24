package ru.ahmedov.maphack.data.global

import ru.ahmedov.maphack.data.prefs.PrefsHelper
import javax.inject.Inject


class GlobalData @Inject constructor(
    private val dataManager: DataManager,
    private val prefsHelper: PrefsHelper
) {}