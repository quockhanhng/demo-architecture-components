package com.khanhnq.demo_architecture_components.utils

import android.content.Context
import com.khanhnq.demo_architecture_components.data.repository.LaunchRepository
import com.khanhnq.demo_architecture_components.data.source.local.database.AppDatabase
import com.khanhnq.demo_architecture_components.data.source.remote.ApiService
import com.khanhnq.demo_architecture_components.data.source.remote.LaunchRemoteDataSource
import com.khanhnq.demo_architecture_components.viewmodel.LaunchDetailViewModelFactory
import com.khanhnq.demo_architecture_components.viewmodel.LaunchesViewModelFactory

object Injector {
    fun provideLaunchesViewModelFactory(context: Context) =
        LaunchesViewModelFactory(getLaunchRepository(context))
    
    fun provideLaunchDetailViewModelFactory(context: Context) =
        LaunchDetailViewModelFactory(getLaunchRepository(context))

    private fun getLaunchRepository(context: Context): LaunchRepository {
        return LaunchRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).launchDao(),
            LaunchRemoteDataSource.getInstance(ApiService.makeService())
        )
    }
}
