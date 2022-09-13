package com.login.read.write.file.jpg.png.myapplication.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.login.read.write.file.jpg.png.myapplication.view.login.LoginFragment

class AppScreensImpl: AppScreens {
    override fun loginScreen() = FragmentScreen {
        LoginFragment.newInstance()
    }
}