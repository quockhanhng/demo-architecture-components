package com.khanhnq.demo_architecture_components.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khanhnq.demo_architecture_components.model.LaunchItemMapper
import com.khanhnq.domain.usecase.launch.GetAllLaunchesUseCase

@Suppress("UNCHECKED_CAST")
class LaunchesViewModelFactory(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    private val itemMapper: LaunchItemMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchesViewModel(getAllLaunchesUseCase, itemMapper) as T
    }
}
