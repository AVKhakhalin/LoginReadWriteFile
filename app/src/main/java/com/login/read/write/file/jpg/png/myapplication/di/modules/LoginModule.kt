package com.login.read.write.file.jpg.png.myapplication.di.modules

import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.di.scope.LoginScope
import com.login.read.write.file.jpg.png.myapplication.di.scope.containers.LoginScopeContainer
import dagger.Module
import dagger.Provides

@Module
abstract class LoginModule {
    companion object {
        @LoginScope
        @Provides
        fun scopeContainer(app: App): LoginScopeContainer = app
    }
}