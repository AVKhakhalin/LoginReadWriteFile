package com.login.read.write.file.jpg.png.myapplication.view.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.login.read.write.file.jpg.png.myapplication.databinding.ItemLoginBinding
import com.login.read.write.file.jpg.png.myapplication.model.LoginModel

class LoginAdapter(
    private val itemClickListener: (LoginModel) -> Unit,
): ListAdapter<LoginModel, LoginAdapter.LoginViewHolder>(LoginItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginViewHolder {
        return LoginViewHolder(
            ItemLoginBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun appendItem(newLogin: LoginModel) {
        val newList: MutableList<LoginModel> = mutableListOf()
        newList.add(newLogin)
        currentList.forEach {
            newList.add(it)
        }
        submitList(newList)
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        holder.showRepo(currentList[position])
    }

    inner class LoginViewHolder(private val itemLogin: ItemLoginBinding):
        RecyclerView.ViewHolder(itemLogin.root) {

        fun showRepo(login: LoginModel) {
            itemLogin.itemLoginContainer.setOnClickListener { itemClickListener(login) }
            itemLogin.itemLoginTitle.text = login.login
        }
    }
}

object LoginItemCallback: DiffUtil.ItemCallback<LoginModel>() {

    override fun areItemsTheSame(oldItem: LoginModel, newItem: LoginModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LoginModel, newItem: LoginModel): Boolean {
        return oldItem == newItem
    }
}