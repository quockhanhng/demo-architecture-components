package com.khanhnq.demo_architecture_components.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.khanhnq.demo_architecture_components.R
import com.khanhnq.demo_architecture_components.utils.Injector
import com.khanhnq.demo_architecture_components.utils.Result
import com.khanhnq.demo_architecture_components.utils.toast
import com.khanhnq.demo_architecture_components.viewmodel.LaunchesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: LaunchesViewModel by viewModels {
        Injector.provideLaunchesViewModelFactory(this)
    }

    private var adapter = LaunchesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        observeData()
    }

    private fun setUpRecyclerView() {
        adapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
        recyclerView.adapter = adapter
    }

    private fun observeData() = with(viewModel) {
        launches.observe(this@MainActivity, {
            when (it?.status) {
                Result.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.submitList(it.data)
                }
                Result.Status.ERROR -> it.message?.let { msg -> this@MainActivity.toast(msg) }
                Result.Status.LOADING -> progressBar.visibility = View.VISIBLE
            }
        })
    }
}
