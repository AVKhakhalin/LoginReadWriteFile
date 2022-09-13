package com.login.read.write.file.jpg.png.myapplication.utils.resouces

import android.content.Context
import javax.inject.Inject

class ResourcesProviderImpl @Inject constructor(
    private val appContext: Context
): ResourcesProvider {
    override fun getString(id: Int): String {
        return appContext.getString(id)
    }

    override fun getContext(): Context {
        return appContext
    }
}