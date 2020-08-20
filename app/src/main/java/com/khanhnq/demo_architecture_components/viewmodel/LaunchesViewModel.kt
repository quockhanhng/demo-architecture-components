package com.khanhnq.demo_architecture_components.viewmodel

import androidx.lifecycle.ViewModel
import com.khanhnq.demo_architecture_components.data.repository.LaunchRepository

class LaunchesViewModel(
    private val launchRepository: LaunchRepository
) : ViewModel() {
    val launches get() = launchRepository.getLaunches()
}
