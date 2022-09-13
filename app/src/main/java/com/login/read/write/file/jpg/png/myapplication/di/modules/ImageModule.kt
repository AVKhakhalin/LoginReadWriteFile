package com.login.read.write.file.jpg.png.myapplication.di.modules

import android.widget.ImageView
import com.login.read.write.file.jpg.png.myapplication.utils.imageloader.ImageLoader
import com.login.read.write.file.jpg.png.myapplication.utils.imageloader.ImageLoaderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun glide(): ImageLoader<ImageView> {
        return ImageLoaderImpl()
    }
}