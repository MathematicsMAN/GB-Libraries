package com.example.gb_libs.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
        val gitUser = arguments?.getParcelable<GitHubUser>(KEY_USER_GITHUB)
        vb?.userName?.text = "$gitUser test userName"
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
    
    companion object {
        fun newInstance(s: String): UserFragment {
            return UserFragment().apply {
                arguments = bundleOf(Pair(KEY_USER_GITHUB, s))
            }
        }
        const val KEY_USER_GITHUB = "USER_GIT"
    }
}