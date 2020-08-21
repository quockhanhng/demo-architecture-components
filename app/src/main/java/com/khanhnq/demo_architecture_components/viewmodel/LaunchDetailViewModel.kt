package com.khanhnq.demo_architecture_components.viewmodel

import androidx.lifecycle.*
import com.khanhnq.demo_architecture_components.data.model.Launch
import com.khanhnq.demo_architecture_components.data.repository.LaunchRepository
import com.khanhnq.demo_architecture_components.utils.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchDetailViewModel(
    private val launchRepository: LaunchRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _launch = _id.switchMap { id ->
        launchRepository.getLaunch(id)
    }

    val launch: LiveData<Result<Launch>> get() = _launch

    fun getLaunch(id: String) {
        _id.value = id
    }
}
