package com.login.read.write.file.jpg.png.myapplication.di.components

import com.login.read.write.file.jpg.png.myapplication.di.modules.ElaborateImageModule
import com.login.read.write.file.jpg.png.myapplication.di.scope.ChooseImageScope
import com.login.read.write.file.jpg.png.myapplication.view.elaborateimage.ElaborateImagePresenter
import dagger.Subcomponent

@ChooseImageScope
@Subcomponent(
    modules = [
        ElaborateImageModule::class
    ]
)
interface ElaborateImageSubcomponent {
    fun provideElaborateImagePresenter(): ElaborateImagePresenter
}