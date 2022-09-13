package com.login.read.write.file.jpg.png.myapplication.view.activity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router
): MvpPresenter<MainView>() {
    fun backPressed() {
        router.exit()
    }
}