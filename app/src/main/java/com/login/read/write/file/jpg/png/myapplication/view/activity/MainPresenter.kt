package com.login.read.write.file.jpg.png.myapplication.view.activity

import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import com.login.read.write.file.jpg.png.myapplication.utils.REQUEST_CODE
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
): MvpPresenter<MainView>() {

    /** Метод выбора картинки на телефоне */
    fun chooseImageOnPhone() {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(viewState as MainActivity,
            Intent.createChooser(intent, "Выберите jpg файл"),
            REQUEST_CODE, null)
    }

    fun loadFirstFragment() {
        router.navigateTo(appScreens.loginScreen())
    }

    fun backPressed() {
        router.exit()
    }
}