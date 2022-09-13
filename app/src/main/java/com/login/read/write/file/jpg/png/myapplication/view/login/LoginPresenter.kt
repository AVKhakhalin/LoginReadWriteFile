package com.login.read.write.file.jpg.png.myapplication.view.login

import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.model.LoginModel
import moxy.MvpPresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val router: Router
): MvpPresenter<LoginView>() {

    fun onLoginClicked(login: LoginModel) {
    }

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion
}