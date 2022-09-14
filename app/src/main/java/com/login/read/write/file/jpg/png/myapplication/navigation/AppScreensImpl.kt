package com.login.read.write.file.jpg.png.myapplication.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.login.read.write.file.jpg.png.myapplication.view.chooseimage.ChooseImageFragment
import com.login.read.write.file.jpg.png.myapplication.view.elaborateimage.ElaborateImageFragment
import com.login.read.write.file.jpg.png.myapplication.view.login.LoginFragment

class AppScreensImpl: AppScreens {
    override fun loginScreen() = FragmentScreen("LOGIN_SCREEN") {
        LoginFragment.newInstance()
    }

    override fun chooseImageScreen(login: String) = FragmentScreen("CHOOSE_IMAGE_SCREEN") {
        ChooseImageFragment.newInstance(login)
    }

    override fun elaborateImageScreen(url: String) = FragmentScreen("ELABORATE_IMAGE_SCREEN") {
        ElaborateImageFragment.newInstance(url)
    }
}