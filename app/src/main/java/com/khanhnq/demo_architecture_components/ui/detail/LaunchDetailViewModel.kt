package com.khanhnq.demo_architecture_components.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.khanhnq.demo_architecture_components.model.LaunchItem
import com.khanhnq.demo_architecture_components.model.LaunchItemMapper
import com.khanhnq.domain.usecase.base.Result
import com.khanhnq.domain.usecase.launch.GetLaunchByIdUseCase

class LaunchDetailViewModel(
    private val getLaunchByIdUseCase: GetLaunchByIdUseCase,
    private val itemMapper: LaunchItemMapper
) : ViewModel() {

    private val _launch = MutableLiveData<LaunchItem>()
    val launch get() = _launch

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getLaunch(id: String) {
        getLaunchByIdUseCase.execute(id).map { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    _isLoading.value = false
                    _launch.value = result.data?.let { itemMapper.mapToPresentation(it) }
                }
                Result.Status.ERROR -> {
                    _isLoading.value = false
                    _error.value = result.message
                }
                else -> _isLoading.value = true
            }
        }
    }
}
