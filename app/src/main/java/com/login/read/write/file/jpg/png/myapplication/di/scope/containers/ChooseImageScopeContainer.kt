package com.login.read.write.file.jpg.png.myapplication.di.scope.containers

import com.login.read.write.file.jpg.png.myapplication.di.components.ChooseImageSubcomponent

interface ChooseImageScopeContainer {
    fun initChooseImageSubcomponent(): ChooseImageSubcomponent
    fun destroyChooseImageSubcomponent()
}