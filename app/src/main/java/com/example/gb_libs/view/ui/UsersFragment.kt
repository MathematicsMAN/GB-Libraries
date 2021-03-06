package com.example.gb_libs.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs.App
import com.example.gb_libs.model.GitHubUsersRepo
import com.example.gb_libs.presentation.UsersPresenter
import com.example.gb_libs.view.BackButtonListener
import com.example.gb_libs_lesson1.databinding.FragmentUsersBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    val presenter by moxyPresenter {
        UsersPresenter(GitHubUsersRepo(), App.instance.router)
    }

    private val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter

        Toast.makeText(
            requireContext(),
            requireArguments().getString(KEY_ARG),
//            "Test message",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {
        fun newInstance(s: String): UsersFragment {
            return UsersFragment().apply {
                arguments = bundleOf(KEY_ARG to s)
            }
        }

        const val KEY_ARG = "USER_INFO"
    }
}