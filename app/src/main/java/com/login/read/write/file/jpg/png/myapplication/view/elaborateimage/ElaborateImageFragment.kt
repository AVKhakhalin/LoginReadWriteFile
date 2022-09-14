package com.login.read.write.file.jpg.png.myapplication.view.elaborateimage

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.FragmentElaborateImageBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.BUNDLE_URL
import com.login.read.write.file.jpg.png.myapplication.utils.LOG_TAG
import com.login.read.write.file.jpg.png.myapplication.utils.binding.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ElaborateImageFragment: MvpAppCompatFragment(R.layout.fragment_elaborate_image),
    ElaborateImageView, BackButtonListener {
    /** Исходные данные */ //region
    // presenter
    private val presenter by moxyPresenter {
        App.instance.initElaborateImageSubcomponent()
        App.instance.elaborateImageSubcomponent?.provideElaborateImagePresenter()!!
    }
    // binding
    private val binding by viewBinding<FragmentElaborateImageBinding>()
    // Instance фрагмента
    companion object {
        fun newInstance(url: String): ElaborateImageFragment {
            val elaborateImageFragment: ElaborateImageFragment = ElaborateImageFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(BUNDLE_URL, url)
            elaborateImageFragment.arguments = bundle
            return elaborateImageFragment
        }
    }
    //endregion

    //region Установка навигации
    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    /** Отображение загруженной jpg-картинки */
    override fun showImage(bitmap: Bitmap) {
        binding.choosedImageView.setImageBitmap(bitmap)
        binding.loadingProgressbar.visibility = View.INVISIBLE
    }

    /** Вывод сообщений */
    override fun showToastLogMessage(newText: String) {
        Toast.makeText(requireActivity(), newText, Toast.LENGTH_LONG).show()
        Log.d(LOG_TAG, newText)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Запуск обработки картинки
        presenter.loadAndShowImage(arguments?.getString(BUNDLE_URL).toString())
    }

    /** Отображение и скрытие информации о сохранении картинки */
    override fun showSaveInfoElements() {
        binding.infoSaveTitle.visibility = View.VISIBLE
        binding.savingProgressbar.visibility = View.VISIBLE
    }
    override fun hideSaveInfoElements(saveUrl: String) {
        binding.infoSaveTitle.text = saveUrl
        binding.savingProgressbar.visibility = View.INVISIBLE
    }
}