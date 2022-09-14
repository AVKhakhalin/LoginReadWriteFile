package com.login.read.write.file.jpg.png.myapplication.view.login

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.model.LoginModel
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import com.login.read.write.file.jpg.png.myapplication.utils.BUNDLE_LOGIN
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_KEY
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProvider
import moxy.MvpPresenter
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val resourcesProviderImpl: ResourcesProvider
): MvpPresenter<LoginView>() {

    fun onLoginClicked(login: LoginModel) {
        val sharedPreferences: SharedPreferences =
            resourcesProviderImpl.getContext().getSharedPreferences(
                SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(BUNDLE_LOGIN, login.login)
        sharedPreferencesEditor.apply()
        router.navigateTo(appScreens.chooseImageScreen())
    }

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion
}