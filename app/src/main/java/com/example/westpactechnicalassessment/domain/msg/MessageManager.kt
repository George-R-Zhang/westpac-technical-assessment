package com.example.westpactechnicalassessment.domain.msg

import com.example.westpactechnicalassessment.domain.adapterinterface.IRestApiRemote
import timber.log.Timber
import javax.inject.Inject

class MessageManager @Inject constructor(
    private val restApiRemote: IRestApiRemote
){
    suspend fun get(url: String): String? {
        return restApiRemote.get(BASE_URL + url, mapOf()).getOrNull()?.also {
            Timber.d("Get response: $it")
        }
    }

    companion object {
        private const val BASE_URL = "https://random-data-api.com/api/v2/"
    }
}