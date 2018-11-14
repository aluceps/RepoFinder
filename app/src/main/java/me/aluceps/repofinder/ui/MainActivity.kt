package me.aluceps.repofinder.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import me.aluceps.repofinder.R
import me.aluceps.repofinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private val myAdapter: MainAdapter by lazy {
        MainAdapter().apply {
            setOnClickListener(object : MainAdapter.OnClickListener {
                override fun click() {
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)?.run {
            initializeView(this)
        }
    }

    override fun initializeView(binding: ActivityMainBinding) {
        binding.recyclerView.run {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            isMotionEventSplittingEnabled = false
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        setItem()
    }

    override fun setItem() {
//        myAdapter.addEmpty()
        for (i in 0..99) {
            myAdapter.addRepository("$i")
        }
    }
}
