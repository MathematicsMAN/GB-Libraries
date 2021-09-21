package com.example.gb_libs.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_libs.App
import com.example.gb_libs.model.GitHubUser
import com.example.gb_libs.presentation.UserPresenter
import com.example.gb_libs.view.BackButtonListener
import com.example.gb_libs_lesson1.databinding.FragmentUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gitUser = arguments?.getParcelable<GitHubUser>(UsersFragment.KEY_ARG)
        vb?.userName?.text = gitUser?.login + " test userName"
    }

    override fun init() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}