package com.login.read.write.file.jpg.png.myapplication.utils.imageloader

interface ImageLoader<T> {
    fun loadInto(url: String, container: T)
}