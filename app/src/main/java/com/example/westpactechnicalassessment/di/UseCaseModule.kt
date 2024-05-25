package com.example.westpactechnicalassessment.di

import com.example.westpactechnicalassessment.usecase.GetCardsUC
import com.example.westpactechnicalassessment.usecase.IGetCardsUC
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetCardsUCModule {
    @Binds
    abstract fun bindGetCardsUCModule(inst: GetCardsUC): IGetCardsUC
}