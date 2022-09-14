package com.login.read.write.file.jpg.png.myapplication.view.activity

import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
): MvpPresenter<MainView>() {

    fun loadFirstFragment() {
        router.navigateTo(appScreens.loginScreen())
    }

    fun backPressed() {
        router.exit()
    }
}