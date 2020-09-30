package com.khanhnq.demo_architecture_components.utils

import android.content.Context
import com.khanhnq.data.LaunchRepositoryImpl
import com.khanhnq.data.entity.LaunchEntityMapper
import com.khanhnq.data.source.local.database.AppDatabase
import com.khanhnq.data.source.remote.ApiService
import com.khanhnq.data.source.remote.LaunchRemoteDataSource
import com.khanhnq.demo_architecture_components.model.LaunchItemMapper
import com.khanhnq.demo_architecture_components.ui.detail.LaunchDetailViewModelFactory
import com.khanhnq.demo_architecture_components.ui.home.LaunchesViewModelFactory
import com.khanhnq.domain.repository.LaunchRepository
import com.khanhnq.domain.usecase.launch.GetAllLaunchesUseCase
import com.khanhnq.domain.usecase.launch.GetLaunchByIdUseCase

object Injector {
    fun provideLaunchesViewModelFactory(context: Context) =
        LaunchesViewModelFactory(provideGetAllLaunchesUseCase(context), itemMapper)

    fun provideLaunchDetailViewModelFactory(context: Context) =
        LaunchDetailViewModelFactory(provideGetLaunchByIdUseCase(context), itemMapper)

    private fun provideGetAllLaunchesUseCase(context: Context) =
        GetAllLaunchesUseCase(getLaunchRepository(context))

    private fun provideGetLaunchByIdUseCase(context: Context) =
        GetLaunchByIdUseCase(getLaunchRepository(context))

    private fun getLaunchRepository(context: Context): LaunchRepository {
        return LaunchRepositoryImpl.getInstance(
            AppDatabase.getInstance(context.applicationContext).launchDao(),
            remoteDataSource,
            entityMapper
        )
    }

    private val entityMapper by lazy { LaunchEntityMapper() }

    private val itemMapper by lazy { LaunchItemMapper() }

    private val remoteDataSource by lazy { LaunchRemoteDataSource.getInstance(ApiService.makeService()) }
}
