package com.login.read.write.file.jpg.png.myapplication.view.elaborateimage

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ElaborateImageView: MvpView {
    fun showToastLogMessage(newText: String)
    fun showImage(bitmap: Bitmap)
}