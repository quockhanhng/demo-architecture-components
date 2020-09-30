package com.khanhnq.demo_architecture_components.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.khanhnq.demo_architecture_components.R
import com.khanhnq.demo_architecture_components.model.LaunchItem
import com.khanhnq.demo_architecture_components.utils.Injector
import com.khanhnq.demo_architecture_components.utils.toast
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val viewModel: LaunchDetailViewModel by viewModels {
        Injector.provideLaunchDetailViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_ID)?.let { id ->
            viewModel.getLaunch(id)
        }
        observeData()
    }

    private fun observeData() = with(viewModel) {
        isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        launch.observe(viewLifecycleOwner) { launch ->
            bindData(launch)
        }

        error.observe(viewLifecycleOwner) { msg ->
            context?.toast(msg)
        }
    }

    private fun bindData(launch: LaunchItem) {
        textName.text = launch.name
        textDetails.text = launch.details
    }

    companion object {
        private const val ARG_ID = "ARG_ID"
        const val DETAIL_TAG = "FRAGMENT_DETAIL"

        fun newInstance(id: String) = DetailFragment().apply {
            arguments = bundleOf(ARG_ID to id)
        }
    }
}
