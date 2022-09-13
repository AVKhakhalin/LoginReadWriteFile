package com.login.read.write.file.jpg.png.myapplication.view.login

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.model.LoginModel
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
): MvpPresenter<LoginView>() {

    fun onRepoClicked(login: LoginModel) {
        Log.d("mylogs", "Выбран репозиторий ${login.login}")
//        router.navigateTo(appScreens.forksScreen())
    }

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion
}