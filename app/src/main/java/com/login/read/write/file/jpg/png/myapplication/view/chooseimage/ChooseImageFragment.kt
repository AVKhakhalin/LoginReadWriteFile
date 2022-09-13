package com.login.read.write.file.jpg.png.myapplication.view.chooseimage

import android.R.id
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.FragmentChooseImageBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.BUNDLE_LOGIN
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

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFERENCES_KEY, AppCompatActivity.MODE_PRIVATE)
        binding.choosedLoginText.text = sharedPreferences.getString(BUNDLE_LOGIN, "")
    }
}