package com.login.read.write.file.jpg.png.myapplication.di.components

import com.login.read.write.file.jpg.png.myapplication.di.modules.AppModule
import com.login.read.write.file.jpg.png.myapplication.view.activity.MainActivity
import com.login.read.write.file.jpg.png.myapplication.view.activity.MainPresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {
    /** AppComponent */ //region
    fun mainPresenter(): MainPresenter
    fun injectMainActivity(mainActivity: MainActivity)
    //endregion
}