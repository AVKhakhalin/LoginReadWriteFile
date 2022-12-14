package com.login.read.write.file.jpg.png.myapplication.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {
    fun loginScreen(): FragmentScreen
    fun chooseImageScreen(login: String): FragmentScreen
    fun elaborateImageScreen(url: String): FragmentScreen
}