package com.khanhnq.demo_architecture_components.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.khanhnq.demo_architecture_components.model.LaunchItem
import com.khanhnq.demo_architecture_components.model.LaunchItemMapper
import com.khanhnq.domain.usecase.base.Result
import com.khanhnq.domain.usecase.launch.GetAllLaunchesUseCase

class LaunchesViewModel(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    private val itemMapper: LaunchItemMapper
) : ViewModel() {

    private val _launches = MutableLiveData<List<LaunchItem>>()
    val launches: LiveData<List<LaunchItem>> get() = _launches

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        getAllLaunchesUseCase.execute().map { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    _isLoading.postValue(false)
                    _launches.postValue(result.data?.map { itemMapper.mapToPresentation(it) })
                }
                Result.Status.ERROR -> {
                    _isLoading.postValue(false)
                    _error.postValue(result.message)
                }
                else -> _isLoading.postValue(false)
            }
        }
    }
}
