package com.example.gb_libs.ui.screens.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.gb_libs.App
import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.navigation.BackButtonListener
import com.example.gb_libs.ui.screens.repos.ReposPresenter
import com.example.gb_libs.ui.screens.users.UsersPresenter
import com.example.gb_libs_lesson1.databinding.FragmentRepoBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {

    private var vb: FragmentRepoBinding? = null

    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gitRepo = arguments?.getParcelable<GitHubRepo>(KEY_REPO_ARG)
        vb?.tvRepoName?.text = "Repo name:  ${gitRepo?.name}"
        vb?.tvRepoId?.text = "Repo id:    ${gitRepo?.id}"
        vb?.tvRepoForks?.text = "Repo forks: ${gitRepo?.forksCount}"
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
        private const val KEY_REPO_ARG = "REPO_INFO"

        fun newInstance(repo: GitHubRepo): RepoFragment {
            return RepoFragment().apply {
                arguments = bundleOf(KEY_REPO_ARG to repo)
            }
        }
    }
}