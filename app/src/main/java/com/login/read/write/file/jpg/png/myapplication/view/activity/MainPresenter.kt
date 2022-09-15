package com.login.read.write.file.jpg.png.myapplication.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_ISINTENTSENDED
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_KEY
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProvider
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val resourcesProviderImpl: ResourcesProvider
): MvpPresenter<MainView>() {
    /** Исходные данные */ //region
    // Установка признака отправки интента со скрытием данного приложения
    private var isIntentSended: Boolean = false
    //endregion

    // Загрузка первого фрагмента с заданием и выбором логина
    fun loadFirstFragment() {
        router.navigateTo(appScreens.loginScreen())
    }

    // Обработка нажатия системной кнопки Back
    fun backPressed() {
        router.exit()
    }

    //region Методы установки и получения признака установки интента на скрытие приложения
    fun setIsIntentSended(isIntentSended: Boolean) {
        this.isIntentSended = isIntentSended
        val sharedPreferences: SharedPreferences =
            resourcesProviderImpl.getContext().getSharedPreferences(
                SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_ISINTENTSENDED, isIntentSended)
        sharedPreferencesEditor.apply()
    }
    fun getIsIntentSended(): Boolean {
        val sharedPreferences: SharedPreferences =
            resourcesProviderImpl.getContext().getSharedPreferences(
            SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        isIntentSended = sharedPreferences.getBoolean(SHARED_PREFERENCES_ISINTENTSENDED, false)
        return isIntentSended
    }
    //endregion
}