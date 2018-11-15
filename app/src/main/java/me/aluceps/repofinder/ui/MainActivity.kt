package me.aluceps.repofinder.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import me.aluceps.repofinder.MyApplication
import me.aluceps.repofinder.R
import me.aluceps.repofinder.databinding.ActivityMainBinding
import me.aluceps.repofinder.di.ActivityModule
import me.aluceps.repofinder.util.isGone
import me.aluceps.repofinder.util.toGone
import me.aluceps.repofinder.util.toVisible
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    @Inject
    lateinit var presenter: MainPresenter

    private val myAdapter: MainAdapter by lazy {
        MainAdapter().apply {
            setOnClickListener(object : MainAdapter.OnClickListener {
                override fun click() {
                    search()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePresenter()
        initializeView()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun initializePresenter() {
        (application as MyApplication).getComponent()?.plus(ActivityModule(this))?.inject(this)
        presenter.attachView(this)
    }

    override fun initializeView() {
        binding.recyclerView.run {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            isMotionEventSplittingEnabled = false
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        Handler().postDelayed({
            setItem()
        }, 1000)
    }

    override fun setItem() {
        for (i in 0..99) {
            myAdapter.addRepository("$i")
        }
        myAdapter.notifyDataSetChanged()
    }

    override fun search() {
        if (binding.progressBar.isGone()) {
            presenter.search("dagger", 20)
        }
    }

    override fun showProgressBar() {
        binding.progressBar.toVisible()
    }

    override fun hideProgressBar() {
        binding.progressBar.toGone()
    }
}
