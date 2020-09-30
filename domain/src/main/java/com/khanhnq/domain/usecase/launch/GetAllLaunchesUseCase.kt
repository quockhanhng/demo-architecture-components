package com.khanhnq.domain.usecase.launch

import androidx.lifecycle.LiveData
import com.khanhnq.domain.model.Launch
import com.khanhnq.domain.repository.LaunchRepository
import com.khanhnq.domain.usecase.base.Result
import com.khanhnq.domain.usecase.base.UseCaseNoParam

class GetAllLaunchesUseCase(
    private val repository: LaunchRepository
) : UseCaseNoParam<LiveData<Result<List<Launch>>>> {

    override fun execute() = repository.getLaunches()
}
