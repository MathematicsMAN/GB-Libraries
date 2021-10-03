package com.example.gb_libs.ui.screens.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs.App
import com.example.gb_libs.data.GitHubReposRepo
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.navigation.BackButtonListener
import com.example.gb_libs.ui.screens.repos.adapter.ReposRvAdapter
import com.example.gb_libs.utils.AndroidNetworkStatus
import com.example.gb_libs_lesson1.databinding.FragmentReposBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    private var vb: FragmentReposBinding? = null

    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(
            GitHubReposRepo(
                AndroidNetworkStatus(requireContext()),
                GitHubDatabase.getInstance()
            ),
            App.instance.router
        )
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
        var urlRepos: String? = null
        lateinit var user: GitHubUser

        fun newInstance(user: GitHubUser): ReposFragment {
            return ReposFragment().apply {
                urlRepos = user.reposUrl
                this@Companion.user = user
                arguments = bundleOf(Pair(KEY_REPOS_ARG, user))
            }
        }

    }
}