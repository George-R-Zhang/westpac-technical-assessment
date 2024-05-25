package com.example.westpactechnicalassessment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class TechnicalAssessmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())
        Timber.d("TechnicalAssessmentApplication - onCreate")
    }
}