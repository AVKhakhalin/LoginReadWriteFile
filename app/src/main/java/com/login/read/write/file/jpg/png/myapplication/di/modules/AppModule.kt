package com.login.read.write.file.jpg.png.myapplication.di.modules

import android.content.Context
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun context(): Context {
        return app
    }

    @Singleton
    @Provides
    fun app(): App {
        return app
    }

    @Singleton
    @Provides
    fun resProvider(resourcesProviderImpl: ResourcesProvider): ResourcesProvider {
        return resourcesProviderImpl
    }
}