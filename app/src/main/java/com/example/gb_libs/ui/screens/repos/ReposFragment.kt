package com.example.gb_libs.ui.screens.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs.App
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.navigation.BackButtonListener
import com.example.gb_libs.ui.initParams
import com.example.gb_libs.ui.screens.repos.adapter.ReposRvAdapter
import com.example.gb_libs_lesson1.databinding.FragmentReposBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    private var vb: FragmentReposBinding? = null

    private val user by initParams<GitHubUser>()

    private val presenter: ReposPresenter by moxyPresenter {
        App.instance.initRepoSubcomponent()
        ReposPresenter(user).apply {
            App.instance.repoSubcomponent?.inject(this)
        }
    }

    private val adapter by lazy {
        ReposRvAdapter(
            presenter.reposListPresenter
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentReposBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvRepos?.adapter = adapter
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
        private const val KEY_REPOS_ARG = "REPOS"
// todo       var urlRepos: String? = null

        fun newInstance(user: GitHubUser): ReposFragment {
            return ReposFragment().apply {
// todo               urlRepos = user.reposUrl
                arguments = bundleOf(Pair(KEY_REPOS_ARG, user))
            }
        }
    }
}