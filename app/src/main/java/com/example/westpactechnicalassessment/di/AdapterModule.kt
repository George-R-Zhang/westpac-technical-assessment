package com.example.westpactechnicalassessment.di

import com.example.westpactechnicalassessment.adapter.RestApiRemote
import com.example.westpactechnicalassessment.domain.adapterinterface.IRestApiRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RestApiRemoteModule {
    @Binds
    @Singleton
    abstract fun bindRestApiRemote(inst: RestApiRemote): IRestApiRemote
}
