package com.login.read.write.file.jpg.png.myapplication.app

import android.app.Application
import com.login.read.write.file.jpg.png.myapplication.di.components.*
import com.login.read.write.file.jpg.png.myapplication.di.modules.AppModule
import com.login.read.write.file.jpg.png.myapplication.di.scope.containers.ChooseImageScopeContainer
import com.login.read.write.file.jpg.png.myapplication.di.scope.containers.ElaborateImageScopeContainer
import com.login.read.write.file.jpg.png.myapplication.di.scope.containers.LoginScopeContainer

class App: Application(), LoginScopeContainer, ChooseImageScopeContainer,
    ElaborateImageScopeContainer {
    /** Исходные данные */ //region
    // appComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    // LoginSubcomponent
    var loginSubcomponent: LoginSubcomponent? = null
    // ChooseImageSubcomponent
    var chooseImageSubcomponent: ChooseImageSubcomponent? = null
    // ElaborateImageSubcomponent
    var elaborateImageSubcomponent: ElaborateImageSubcomponent? = null
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

    /** Методы ChooseImageScopeContainer */ //region
    override fun initChooseImageSubcomponent() = appComponent.chooseImageSubcomponent().also {
        chooseImageSubcomponent = it
    }
    override fun destroyChooseImageSubcomponent() {
        chooseImageSubcomponent = null
    }
    //endregion

    /** Методы ElaborateImageScopeContainer */ //region
    override fun initElaborateImageSubcomponent() = appComponent.elaborateImageSubcomponent().also {
        elaborateImageSubcomponent = it
    }
    override fun destroyElaborateImageSubcomponent() {
        elaborateImageSubcomponent = null
    }
    //endregion
}