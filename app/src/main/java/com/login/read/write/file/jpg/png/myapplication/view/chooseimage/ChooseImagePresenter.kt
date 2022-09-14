package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class ChooseImagePresenter @Inject constructor(
    private val router: Router,
): MvpPresenter<ChooseImageView>() {

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion

    fun readAndWriteImage() {
        viewState.showToastLogMessage("Получено разрешение на запись и считывание информации")
    }
}