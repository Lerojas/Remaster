package com.angular.globalretail.core.data.dependence

import com.angular.globalretail.core.data.dataaccess.ISynchronizeRepository
import com.angular.globalretail.core.data.repository.SynchronizeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSynchronizerRepository(repositoryImpl: SynchronizeRepository) : ISynchronizeRepository
}