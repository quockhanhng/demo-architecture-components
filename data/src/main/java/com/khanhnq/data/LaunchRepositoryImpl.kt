package com.khanhnq.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.khanhnq.data.entity.LaunchEntityMapper
import com.khanhnq.data.source.local.dao.LaunchDao
import com.khanhnq.data.source.remote.LaunchRemoteDataSource
import com.khanhnq.data.utils.loadData
import com.khanhnq.domain.model.Launch
import com.khanhnq.domain.repository.LaunchRepository
import com.khanhnq.domain.usecase.base.Result

class LaunchRepositoryImpl private constructor(
    private val localDataSource: LaunchDao,
    private val remoteDataSource: LaunchRemoteDataSource,
    private val mapper: LaunchEntityMapper
) : LaunchRepository {

    override fun getLaunch(id: String): LiveData<Result<Launch>> = loadData(
        { localDataSource.getDistinctLaunchById(id).map { mapper.mapToDomain(it) } },
        { remoteDataSource.getLaunch(id) },
        { localDataSource.insert(it) }
    )

    override fun getLaunches(): LiveData<Result<List<Launch>>> = loadData(
        { localDataSource.getAllLaunches().map { list -> list.map { mapper.mapToDomain(it) } } },
        { remoteDataSource.getLaunches() },
        { localDataSource.insertAll(it) }
    )

    companion object {
        @Volatile
        private var instance: LaunchRepositoryImpl? = null

        fun getInstance(
            localDataSource: LaunchDao,
            remoteDataSource: LaunchRemoteDataSource,
            mapper: LaunchEntityMapper
        ) =
            instance ?: synchronized(this) {
                instance ?: LaunchRepositoryImpl(localDataSource, remoteDataSource, mapper).also {
                    instance = it
                }
            }
    }
}
