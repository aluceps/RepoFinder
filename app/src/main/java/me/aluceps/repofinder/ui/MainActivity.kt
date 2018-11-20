package me.aluceps.repofinder.ui

import android.content.SharedPreferences
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import me.aluceps.repofinder.MyApplication
import me.aluceps.repofinder.R
import me.aluceps.repofinder.databinding.ActivityMainBinding
import me.aluceps.repofinder.di.ActivityModule
import me.aluceps.repofinder.model.Repository
import me.aluceps.repofinder.util.ext.setOAuth
import me.aluceps.repofinder.util.ext.toGone
import me.aluceps.repofinder.util.ext.toVisible
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
                override fun click(url: String) {
                    presenter.launchUrl(this@MainActivity, url)
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
        binding.query.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.let { presenter.queryPublisher.onNext(it) }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        presenter.initializePublisher()
    }

    override fun setEmpty() {
        myAdapter.addEmpty()
    }

    override fun setItem(model: Repository) {
        myAdapter.addRepository(model)
    }

    override fun setHeader(number: Long) {
        myAdapter.addHeader(number)
    }

    override fun clear() {
        myAdapter.clear()
    }

    override fun search(query: String) {
        if (query.isEmpty()) {
            clear()
            hideProgressBar()
            presenter.destroy()
        } else {
            presenter.search(query, 100)
        }
    }

    override fun showProgressBar() {
        binding.progressBar.toVisible()
    }

    override fun hideProgressBar() {
        binding.progressBar.toGone()
    }

    override fun showSnackbar(message: String, preferences: SharedPreferences) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).apply {
            setAction(R.string.set_oauth) {
                val editText = EditText(it.context).apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                }
                AlertDialog.Builder(it.context)
                        .setTitle(R.string.dialog_text)
                        .setView(editText)
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .setPositiveButton(R.string.dialog_ok) { _, _ ->
                            editText.text?.toString()?.let {
                                if (it.isNotEmpty()) {
                                    preferences.setOAuth(it)
                                }
                            }
                        }.show()
            }
        }.show()
    }
}
