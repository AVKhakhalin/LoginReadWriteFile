package com.login.read.write.file.jpg.png.myapplication.view.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.FragmentLoginBinding
import com.login.read.write.file.jpg.png.myapplication.model.LoginModel
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.binding.viewBinding
import com.login.read.write.file.jpg.png.myapplication.view.login.adapter.LoginAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment: MvpAppCompatFragment(R.layout.fragment_login), LoginView, BackButtonListener {
    /** Исходные данные */ //region
    // presenter
    private val presenter by moxyPresenter {
        App.instance.initLoginSubcomponent()
        App.instance.loginSubcomponent?.provideLoginPresenter()!!
    }
    // binding
    private val binding by viewBinding<FragmentLoginBinding>()
    // adapter
    private val adapter by lazy {
        LoginAdapter { presenter.onLoginClicked(it) }
    }
    //endregion

    //region Установка навигации
    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
    // Instance фрагмента
    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация списка логинов
        initLoginsList()
        // Установка добавления нового логина
        initAddNewLogin()
    }

    private fun initLoginsList() {
        binding.loginList.layoutManager = LinearLayoutManager(requireContext())
        binding.loginList.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAddNewLogin() {
        binding.newLoginButton.setOnClickListener {
            if (binding.newLoginTextfield.text.toString().isNotEmpty()) {
                adapter.appendItem(LoginModel(binding.newLoginTextfield.text.toString()))
            }
        }
    }

    //region Методы сохранения и загрузки введённых пользователем логинов
    override fun onPause() {
        presenter.saveLogins(adapter.currentList)
        super.onPause()
    }
    override fun onResume() {
        super.onResume()
        adapter.submitList(presenter.loadLogins())
    }
    //endregion
}