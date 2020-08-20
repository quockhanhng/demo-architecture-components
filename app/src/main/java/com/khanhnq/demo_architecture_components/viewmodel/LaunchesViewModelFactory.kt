package com.khanhnq.demo_architecture_components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khanhnq.demo_architecture_components.data.repository.LaunchRepository

@Suppress("UNCHECKED_CAST")
class LaunchesViewModelFactory(private val launchRepository: LaunchRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchesViewModel(launchRepository) as T
    }
}
