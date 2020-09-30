package com.khanhnq.domain.usecase.launch

import androidx.lifecycle.LiveData
import com.khanhnq.domain.model.Launch
import com.khanhnq.domain.repository.LaunchRepository
import com.khanhnq.domain.usecase.base.Result
import com.khanhnq.domain.usecase.base.UseCase

class GetLaunchByIdUseCase(
    private val repository: LaunchRepository
) : UseCase<String, LiveData<Result<Launch>>> {

    override fun execute(launchId: String) = repository.getLaunch(launchId)
}
