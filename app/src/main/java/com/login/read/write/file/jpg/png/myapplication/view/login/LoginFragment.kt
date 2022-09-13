package com.login.read.write.file.jpg.png.myapplication.view.login

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.login.read.write.file.jpg.png.myapplication.R
import com.login.read.write.file.jpg.png.myapplication.app.App
import com.login.read.write.file.jpg.png.myapplication.databinding.FragmentLoginBinding
import com.login.read.write.file.jpg.png.myapplication.navigation.BackButtonListener
import com.login.read.write.file.jpg.png.myapplication.utils.START_LOGINS
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
        LoginAdapter { presenter.onRepoClicked(it) }
    }
    //endregion

    //region Установка навигации
    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
    // Instance фрагмента
    companion object {
        fun newInstance() = LoginFragment()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** Установка списка репозиториев пользователя */
        binding.loginList.layoutManager = LinearLayoutManager(requireContext())
        binding.loginList.adapter = adapter
        adapter.submitList(START_LOGINS)
    }
}