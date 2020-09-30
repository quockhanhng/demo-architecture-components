package com.khanhnq.demo_architecture_components.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khanhnq.demo_architecture_components.model.LaunchItemMapper
import com.khanhnq.domain.usecase.launch.GetLaunchByIdUseCase

@Suppress("UNCHECKED_CAST")
class LaunchDetailViewModelFactory(
    private val getLaunchByIdUseCase: GetLaunchByIdUseCase,
    private val itemMapper: LaunchItemMapper
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchDetailViewModel(getLaunchByIdUseCase, itemMapper) as T
    }
}
