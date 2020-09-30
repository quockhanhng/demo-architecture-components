package com.khanhnq.domain.repository

import androidx.lifecycle.LiveData
import com.khanhnq.domain.model.Launch
import com.khanhnq.domain.usecase.base.Result

interface LaunchRepository {
    fun getLaunch(id: String): LiveData<Result<Launch>>
    
    fun getLaunches(): LiveData<Result<List<Launch>>>
}
