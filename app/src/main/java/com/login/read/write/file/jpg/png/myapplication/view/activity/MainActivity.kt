package com.login.read.write.file.jpg.png.myapplication.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.ActivityMainBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.REQUEST_CODE
import com.login.read.write.file.jpg.png.myapplication.utils.getNameFromUri
import com.login.read.write.file.jpg.png.myapplication.utils.getPathFromUri
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

        // Загрузка первого фрагмента (LoginFragment)
        presenter.loadFirstFragment()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE) && (resultCode == RESULT_OK)) {
            Toast.makeText(this, "$data", Toast.LENGTH_SHORT).show()
            Log.d("mylogs", "NAME: ${data?.data?.getNameFromUri(this)}")
            Log.d("mylogs", "PATH: ${data?.data?.getPathFromUri()}")
            Log.d("mylogs", "PATH (LONG): ${data?.data}")
            Log.d("mylogs", "PATH (SHORT): ${data?.data?.path}")
            val fileUri: String = data?.data?.path.toString()
            var path: String = ""
            var correctUri: Uri? = data?.data
            if (fileUri.indexOf(":") != -1) {
                path = fileUri.substring(0, fileUri.indexOf(":"))
                Log.d("mylogs", "PATH (CUTTING): $path")
//                correctUri = Uri.fromFile(File("$path/${data?.data?.getNameFromUri(this)}"))
                correctUri = Uri.parse(Uri.decode("content://com.android.providers.media.documents/document/image/IMG_20220913_115929.jpg"))
            }
            Log.d("mylogs", "CORRECTED URI: $correctUri")
//            presenter.loadAndShowImage(correctUri)
        }
    }
}