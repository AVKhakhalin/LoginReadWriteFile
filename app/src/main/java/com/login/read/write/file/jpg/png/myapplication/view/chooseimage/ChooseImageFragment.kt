package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.FragmentChooseImageBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.BUNDLE_LOGIN
import com.login.read.write.file.jpg.png.myapplication.utils.LOG_TAG
import com.login.read.write.file.jpg.png.myapplication.utils.NAME_INPUT_FILE_EXTENTION
import com.login.read.write.file.jpg.png.myapplication.utils.REQUEST_CODE
import com.login.read.write.file.jpg.png.myapplication.utils.binding.viewBinding
import moxy.MvpAppCompatActivity
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
        fun newInstance(login: String): ChooseImageFragment {
            val chooseImageFragment: ChooseImageFragment = ChooseImageFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(BUNDLE_LOGIN, login)
            chooseImageFragment.arguments = bundle
            return chooseImageFragment
        }
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
        binding.choosedLoginText.text = arguments?.getString(BUNDLE_LOGIN)
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
                showToastLogMessage(requireActivity().getString(R.string.access_read_write_ok))
                true
            } else {
                showToastLogMessage(requireActivity().getString(R.string.access_read_write_not))
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1)
                false
            }
        } else {
            showToastLogMessage(requireActivity().getString(R.string.access_read_write_ok))
            true
        }
    }

    /** Метод выбора картинки на телефоне */
    override fun chooseImageOnPhone() {
        val intent = Intent()
            .setType(NAME_INPUT_FILE_EXTENTION)
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, requireActivity().getString(
            R.string.choose_jpg_file)), REQUEST_CODE, null)
    }

    /** Вывод сообщений */
    override fun showToastLogMessage(newText: String) {
        Toast.makeText(requireActivity(), newText, Toast.LENGTH_LONG).show()
        Log.d(LOG_TAG, newText)
    }

    /** Получение информации о выборанной пользователем картинке */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE) && (resultCode == MvpAppCompatActivity.RESULT_OK)) {
            presenter.loadElaborateImageFragment(data?.data)
        }
    }
}