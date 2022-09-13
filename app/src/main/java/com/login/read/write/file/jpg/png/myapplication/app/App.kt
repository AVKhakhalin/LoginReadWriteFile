package com.login.read.write.file.jpg.png.myapplication.app

import android.app.Application
import com.login.read.write.file.jpg.png.myapplication.di.components.*
import com.login.read.write.file.jpg.png.myapplication.di.modules.AppModule
import com.login.read.write.file.jpg.png.myapplication.di.scope.containers.LoginScopeContainer

class App: Application(), LoginScopeContainer {
    /** Исходные данные */ //region
    // appComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    // LoginSubcomponent
    var loginSubcomponent: LoginSubcomponent? = null
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

    /** Методы LoginScopeContainer */ //region
    override fun initLoginSubcomponent() = appComponent.loginSubcomponent().also {
        loginSubcomponent = it
    }
    override fun destroyLoginSubcomponent() {
        loginSubcomponent = null
    }
    //endregion
}