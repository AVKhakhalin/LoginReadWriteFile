package com.login.read.write.file.jpg.png.myapplication.app

import android.app.Application
import com.login.read.write.file.jpg.png.myapplication.di.components.*
import com.login.read.write.file.jpg.png.myapplication.di.modules.AppModule

class App: Application() {
    /** Исходные данные */ //region
    // appComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    //endregion

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}