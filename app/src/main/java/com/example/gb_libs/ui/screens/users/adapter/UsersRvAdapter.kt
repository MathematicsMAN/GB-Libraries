package com.example.gb_libs.ui.screens.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs.ui.items.IUserListPresenter
import com.example.gb_libs.ui.images.GlideImageLoader
import com.example.gb_libs_lesson1.databinding.ItemUserBinding

class UsersRvAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: GlideImageLoader
    ) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadTo(url, vb.avatarImageView)
        }
    }
}