package com.example.westpactechnicalassessment.adapter

import com.example.westpactechnicalassessment.domain.adapterinterface.IRestApiRemote
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.DEFAULT
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import timber.log.Timber
import javax.inject.Inject

class RestApiRemote @Inject constructor() : IRestApiRemote {

    private val client: HttpClient by lazy {
        HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    override suspend fun get(url: String, headers: Map<String, String>): Result<String> {
        Timber.d("GET: $url")
        return withContext(Dispatchers.IO) {
            runCatching {
                val httpResponse = client.get(url) {
                    contentType(ContentType.Application.Json)
                    headers {
                        headers.forEach { mapEntry ->
                            append(mapEntry.key, mapEntry.value)
                        }
                    }
                }
                httpResponse.bodyAsText()
            }
        }
    }
}