package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.FragmentChooseImageBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.BUNDLE_LOGIN
import com.login.read.write.file.jpg.png.myapplication.utils.LOG_TAG
import com.login.read.write.file.jpg.png.myapplication.utils.SHARED_PREFERENCES_KEY
import com.login.read.write.file.jpg.png.myapplication.utils.binding.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ChooseImageFragment: MvpAppCompatFragment(R.layout.fragment_choose_image), ChooseImageView,
    BackButtonListener {
    /** Исходные данные */ //region
    // presenter
    private val presenter by moxyPresenter {
        App.instance.initChooseImageSubcomponent()
        App.instance.chooseImageSubcomponent?.provideChooseImagePresenter()!!
    }
    // binding
    private val binding by viewBinding<FragmentChooseImageBinding>()
    // Instance фрагмента
    companion object {
        fun newInstance(): ChooseImageFragment = ChooseImageFragment()
    }
    //endregion

    //region Установка навигации
    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение логина
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        binding.choosedLoginText.text = sharedPreferences.getString(BUNDLE_LOGIN, "")

        // Инициализация кнопки загрузки картинки
        initImageButton()
    }

    private fun initImageButton() {
        binding.chooseImageButton.setOnClickListener {
            if (isStoragePermissionGranted()) {
                presenter.readAndWriteImage()
            }
        }
    }

    /** Получение разрешений на запись и считывание информации с телефона */
    private fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requireActivity().checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showToastLogMessage("Разрешение на запись и считывание данных получено")
                true
            } else {
                showToastLogMessage("Разрешение на запись и считывание данных отсутствует")
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1)
                false
            }
        } else {
            showToastLogMessage("Разрешение на запись и считывание данных получено")
            true
        }
    }

    /** Вывод сообщений */
    override fun showToastLogMessage(newText: String) {
        Toast.makeText(requireActivity(), newText, Toast.LENGTH_LONG).show()
        Log.d(LOG_TAG, newText)
    }

}