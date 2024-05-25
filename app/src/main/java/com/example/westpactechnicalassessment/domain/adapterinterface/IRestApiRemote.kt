package com.example.westpactechnicalassessment.domain.adapterinterface

interface IRestApiRemote {
    suspend fun get(url: String, headers: Map<String, String>) : Result<String>
}