package com.example.gb_libs.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs.presentation.IUserListPresenter
import com.example.gb_libs.view.UserItemView
import com.example.gb_libs_lesson1.databinding.ItemUserBinding

class UsersRvAdapter(private val presenter: IUserListPresenter): RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersRvAdapter.ViewHolder {
        return ViewHolder(ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun onBindViewHolder(holder: UsersRvAdapter.ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding): RecyclerView.ViewHolder(vb.root), UserItemView {
        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override var pos: Int = -1

    }
}