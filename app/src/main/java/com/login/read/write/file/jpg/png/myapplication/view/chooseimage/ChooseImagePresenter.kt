package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import android.net.Uri
import android.os.Environment
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class ChooseImagePresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens
): MvpPresenter<ChooseImageView>() {
    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion

    fun readAndWriteImage() {
        viewState.showToastLogMessage("Получено разрешение на запись и считывание информации")
        // Проверка доступности SD-диска
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            viewState.showToastLogMessage("Выбор фотографий возможен только с SD-диска.\n" +
                "Сейчас он не доступен. Пожалуйста, подключите SD-диск для работы программы.")
            return
        }
        // Выбор картинки на SD-диске
        viewState.chooseImageOnPhone()
    }

    fun loadElaborateImageFragment(correctUri: Uri?) {
        if (correctUri != null) {
            router.navigateTo(appScreens.elaborateImageScreen(correctUri.toString()))
        } else {
            viewState.showToastLogMessage("Не удаётся загрузить выбранную картинку")
        }
    }
}