package com.example.westpactechnicalassessment.di

import com.example.westpactechnicalassessment.adapter.RestApiRemote
import com.example.westpactechnicalassessment.domain.adapterinterface.IRestApiRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RestApiRemoteModule {
    @Binds
    abstract fun bindRestApiRemote(inst: RestApiRemote): IRestApiRemote
}
