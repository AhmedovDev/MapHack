package ru.ahmedov.maphack.di.global

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ahmedov.deliveryapp.di.global.modules.*
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        GlobalDataModule::class,
        PrefsModule::class,
        DataModule::class
    ]
)
@Singleton
interface AppComponent {


    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun bindsInstanceContext(context: Context): Builder
    }
}