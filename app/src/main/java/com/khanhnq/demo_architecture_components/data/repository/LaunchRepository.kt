package com.khanhnq.demo_architecture_components.data.repository

import com.khanhnq.demo_architecture_components.data.source.local.dao.LaunchDao
import com.khanhnq.demo_architecture_components.data.source.remote.LaunchRemoteDataSource
import com.khanhnq.demo_architecture_components.utils.loadData

class LaunchRepository private constructor(
    private val localDataSource: LaunchDao,
    private val remoteDataSource: LaunchRemoteDataSource
) {
    fun getLaunch(id: String) = loadData(
        { localDataSource.getDistinctLaunchById(id) },
        { remoteDataSource.getLaunch(id) },
        { localDataSource.insert(it) }
    )

    fun getLaunches() = loadData(
        { localDataSource.getAllLaunches() },
        { remoteDataSource.getLaunches() },
        { localDataSource.insertAll(it) }
    )

    companion object {
        @Volatile
        private var instance: LaunchRepository? = null

        fun getInstance(localDataSource: LaunchDao, remoteDataSource: LaunchRemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: LaunchRepository(localDataSource, remoteDataSource).also {
                    instance = it
                }
            }
    }
}
