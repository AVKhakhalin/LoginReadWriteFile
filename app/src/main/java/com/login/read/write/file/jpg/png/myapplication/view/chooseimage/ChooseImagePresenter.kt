package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import android.net.Uri
import android.os.Environment
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProvider
import moxy.MvpPresenter
import javax.inject.Inject

class ChooseImagePresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val resourcesProviderImpl: ResourcesProvider
): MvpPresenter<ChooseImageView>() {
    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion

    fun chooseImage() {
        // Проверка доступности SD-диска
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            viewState.showToastLogMessage(resourcesProviderImpl.getContext().
                getString(R.string.error_sd_not_access))
            return
        }
        // Выбор картинки на SD-диске
        viewState.chooseImageOnPhone()
    }

    fun loadElaborateImageFragment(uri: Uri?) {
        if (uri != null) {
            router.navigateTo(appScreens.elaborateImageScreen(uri.toString()))
        } else {
            viewState.showToastLogMessage(resourcesProviderImpl.getContext().
                getString(R.string.error_image_cant_loaded))
        }
    }
}