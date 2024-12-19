package com.angular.globalretail.core.data.dependence

import com.angular.globalretail.core.data.dataaccess.IConfigRepository
import com.angular.globalretail.core.data.repository.ConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindConfigRepository(repositoryImpl: ConfigRepository) : IConfigRepository
}