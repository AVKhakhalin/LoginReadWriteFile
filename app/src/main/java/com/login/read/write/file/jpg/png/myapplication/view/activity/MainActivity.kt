package com.login.read.write.file.jpg.png.myapplication.view.activity

import android.os.Bundle
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity: MvpAppCompatActivity(R.layout.activity_main), MainView {
    /** Исходные данные */ //region
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
}