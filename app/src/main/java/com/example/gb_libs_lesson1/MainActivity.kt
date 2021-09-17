package com.example.gb_libs_lesson1

import android.os.Bundle
import android.view.View
import com.example.gb_libs_lesson1.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter {
        MainPresenter(CountersModel())
    }

    private var _vb: ActivityMainBinding? = null

    private val vb
        get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        val listener = View.OnClickListener {
            when (it.id) {
                R.id.btn_counter1 -> {
                    presenter.counterClick(0)
                }
                R.id.btn_counter2 -> {
                    presenter.counterClick(1)
                }
                R.id.btn_counter3 -> {
                    presenter.counterClick(2)
                }
            }
        }

        vb.btnCounter1.setOnClickListener(listener)
        vb.btnCounter2.setOnClickListener(listener)
        vb.btnCounter3.setOnClickListener(listener)
    }

    override fun setButton1Text(text: String) {
        vb.btnCounter1.text = text
    }

    override fun setButton2Text(text: String) {
        vb.btnCounter2.text = text
    }

    override fun setButton3Text(text: String) {
        vb.btnCounter3.text = text
    }
}