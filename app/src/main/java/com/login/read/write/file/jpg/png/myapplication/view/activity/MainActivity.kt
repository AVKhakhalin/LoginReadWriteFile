package com.login.read.write.file.jpg.png.myapplication.view.activity

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.ActivityMainBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity: MvpAppCompatActivity(R.layout.activity_main), MainView {
    /** Исходные данные */ //region
    // navigator
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = AppNavigator(this@MainActivity, R.id.container)
    private var isOnlyOneFragmentExist: Boolean = false
    // Binding
    private lateinit var binding: ActivityMainBinding
    // moxyPresenter
    private val presenter by moxyPresenter {
        App.instance.appComponent.mainPresenter()
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.injectMainActivity(this@MainActivity)
        // Подключение binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Отображение содержимого окна
        setContentView(binding.root)

        if (savedInstanceState == null) {
            // Обнуление признака посылки интента на выбор картинки
            setIsIntentSended(false)
            // Загрузка первого фрагмента (LoginFragment)
            presenter.loadFirstFragment()
        }
    }

    //region Установка навигации
    override fun onResumeFragments() {
        super.onResumeFragments()
        // Установка навигатора
        navigatorHolder.setNavigator(navigator)
    }
    override fun onPause() {
        // Удаление навигатора
        navigatorHolder.removeNavigator()
        super.onPause()
    }
    override fun onBackPressed() {
        if (isOnlyOneFragmentExist) finish()
        isOnlyOneFragmentExist = supportFragmentManager.fragments.size == 1
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
    //endregion

    //region Методы установки и получения признака установки интента на скрытие приложения
    fun setIsIntentSended(isIntentSended: Boolean) {
        presenter.setIsIntentSended(isIntentSended)
    }
    fun getIsIntentSended() = presenter.getIsIntentSended()
    //endregion
}