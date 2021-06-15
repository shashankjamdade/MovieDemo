package com.demo.di

import com.demo.api.NetworkAPIService
import com.demo.listener.CommonRepository
import com.demo.repository.CommonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideDataRepository(apiService: NetworkAPIService): CommonRepository {
        return CommonRepositoryImpl(apiService) as CommonRepository
    }

}