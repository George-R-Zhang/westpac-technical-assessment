package com.example.westpactechnicalassessment.di

import com.example.westpactechnicalassessment.usecase.GetCreditCardsUC
import com.example.westpactechnicalassessment.usecase.IGetCreditCardsUC
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetCreditCardsUCModule {
    @Binds
    abstract fun bindGetCreditCardsUCModule(inst: GetCreditCardsUC): IGetCreditCardsUC
}