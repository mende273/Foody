package mende273.foody

import android.app.Application
import mende273.foody.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FoodyApplication)
            modules(appModule)
        }
    }
}
