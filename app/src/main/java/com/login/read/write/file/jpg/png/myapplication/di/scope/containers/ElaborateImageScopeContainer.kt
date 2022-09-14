package com.login.read.write.file.jpg.png.myapplication.di.scope.containers

import com.login.read.write.file.jpg.png.myapplication.di.components.ElaborateImageSubcomponent

interface ElaborateImageScopeContainer {
    fun initElaborateImageSubcomponent(): ElaborateImageSubcomponent
    fun destroyElaborateImageSubcomponent()
}