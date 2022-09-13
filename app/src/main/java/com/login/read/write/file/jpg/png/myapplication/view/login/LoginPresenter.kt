package com.login.read.write.file.jpg.png.myapplication.view.login

import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
): MvpPresenter<LoginView>() {

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion
}