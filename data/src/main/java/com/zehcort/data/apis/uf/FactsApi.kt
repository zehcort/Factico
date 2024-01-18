package com.zehcort.data.apis.uf

import com.zehcort.data.entities.uf.Fact
import retrofit2.http.GET

interface FactsApi {
    @GET(value = Constants.UF_RANDOM_FACT)
    suspend fun getRandomFact(): Fact
}