package com.login.read.write.file.jpg.png.myapplication.di.scope.containers

import com.login.read.write.file.jpg.png.myapplication.di.components.LoginSubcomponent

interface LoginScopeContainer {
    fun initLoginSubcomponent(): LoginSubcomponent
    fun destroyLoginSubcomponent()
}