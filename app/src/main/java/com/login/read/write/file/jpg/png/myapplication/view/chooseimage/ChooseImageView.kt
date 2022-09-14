package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ChooseImageView: MvpView {
    fun showToastLogMessage(newText: String)
}
