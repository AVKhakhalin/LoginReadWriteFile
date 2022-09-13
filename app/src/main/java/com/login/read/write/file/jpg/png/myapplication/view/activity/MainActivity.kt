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
    }

    //region Установка навигации
    override fun onResume() {
        super.onResume()
        // Установка навигатора
        navigatorHolder.setNavigator(navigator)
    }
    override fun onPause() {
        super.onPause()
        // Удаление навигатора
        navigatorHolder.removeNavigator()
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
}