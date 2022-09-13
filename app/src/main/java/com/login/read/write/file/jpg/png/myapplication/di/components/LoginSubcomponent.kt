package com.login.read.write.file.jpg.png.myapplication.di.components

import com.login.read.write.file.jpg.png.myapplication.di.modules.LoginModule
import com.login.read.write.file.jpg.png.myapplication.di.scope.LoginScope
import com.login.read.write.file.jpg.png.myapplication.view.login.LoginPresenter
import dagger.Subcomponent

@LoginScope
@Subcomponent(
    modules = [
        LoginModule::class
    ]
)
interface LoginSubcomponent {
    fun provideLoginPresenter(): LoginPresenter
}