package com.login.read.write.file.jpg.png.myapplication.view.login

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Router
import com.login.read.write.file.jpg.png.myapplication.model.LoginModel
import com.login.read.write.file.jpg.png.myapplication.navigation.AppScreens
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_KEY
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_LOGINS_KEY
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_LOGINS_NUMBER_KEY
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProvider
import com.login.read.write.file.jpg.png.myapplication.utils.resouces.ResourcesProviderImpl
import moxy.MvpPresenter
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val resourcesProviderImpl: ResourcesProvider
): MvpPresenter<LoginView>() {

    fun onLoginClicked(login: LoginModel) {
        router.navigateTo(appScreens.chooseImageScreen(login.login))
    }

    //region Установка навигации
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    //endregion

    fun saveLogins(loginList: List<LoginModel>) {
        val sharedPreferences: SharedPreferences =
            resourcesProviderImpl.getContext().getSharedPreferences(
                SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putInt(SHARED_PREFERENCES_LOGINS_NUMBER_KEY, loginList.size)
        loginList.forEachIndexed { index, it ->
            sharedPreferencesEditor.putString("$SHARED_PREFERENCES_LOGINS_KEY$index", it.login)
        }
        sharedPreferencesEditor.apply()
    }
    fun loadLogins(): List<LoginModel> {
        val loginList: MutableList<LoginModel> = mutableListOf()
        val sharedPreferences: SharedPreferences =
            resourcesProviderImpl.getContext().getSharedPreferences(
                SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        repeat(sharedPreferences.getInt(SHARED_PREFERENCES_LOGINS_NUMBER_KEY, 0)) { index ->
            loginList.add(LoginModel(sharedPreferences.getString(
                "$SHARED_PREFERENCES_LOGINS_KEY$index", "").toString()))
        }
        return loginList
    }
}