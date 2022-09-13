package com.login.read.write.file.jpg.png.myapplication.utils.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageLoaderImpl: ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(url)
            .circleCrop()
            .into(container)
    }
}