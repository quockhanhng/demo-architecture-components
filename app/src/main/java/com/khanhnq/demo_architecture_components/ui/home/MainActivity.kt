package com.khanhnq.demo_architecture_components.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.khanhnq.demo_architecture_components.R
import com.khanhnq.demo_architecture_components.ui.detail.DetailFragment
import com.khanhnq.demo_architecture_components.ui.detail.DetailFragment.Companion.DETAIL_TAG
import com.khanhnq.demo_architecture_components.ui.home.adapter.LaunchesAdapter
import com.khanhnq.demo_architecture_components.utils.Injector
import com.khanhnq.demo_architecture_components.utils.toast
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
        recyclerView.adapter = adapter.apply {
            onItemClick = { launchId -> goToDetail(launchId) }
        }
    }

    private fun goToDetail(id: String) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.root, DetailFragment.newInstance(id), DETAIL_TAG)
            addToBackStack(null)
            commit()
        }
    }

    private fun observeData() = with(viewModel) {
        
        isLoading.observe(this@MainActivity) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        launches.observe(this@MainActivity) { list ->
            adapter.submitList(list)
        }

        error.observe(this@MainActivity) { msg ->
            toast(msg)
        }
    }
}
