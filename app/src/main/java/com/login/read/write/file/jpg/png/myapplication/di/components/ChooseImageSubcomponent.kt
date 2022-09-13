package com.login.read.write.file.jpg.png.myapplication.di.components

import com.login.read.write.file.jpg.png.myapplication.di.modules.ChooseImageModule
import com.login.read.write.file.jpg.png.myapplication.di.scope.ChooseImageScope
import com.login.read.write.file.jpg.png.myapplication.view.chooseimage.ChooseImagePresenter
import dagger.Subcomponent

@ChooseImageScope
@Subcomponent(
    modules = [
        ChooseImageModule::class
    ]
)
interface ChooseImageSubcomponent {
    fun provideChooseImagePresenter(): ChooseImagePresenter
}