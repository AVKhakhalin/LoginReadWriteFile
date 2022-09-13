package com.login.read.write.file.jpg.png.myapplication.utils.resouces

import android.content.Context
import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes id: Int): String
    fun getContext(): Context
}