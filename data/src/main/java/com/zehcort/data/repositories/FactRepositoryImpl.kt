package com.zehcort.data.repositories

import com.zehcort.data.apis.uf.FactsApi
import com.zehcort.data.dtos.toDomain
import com.zehcort.domain.models.Fact
import com.zehcort.domain.repositories.FactsRepository
import javax.inject.Inject

class FactsRepositoryImpl @Inject constructor(
    private val ufFactsApi: FactsApi
) : FactsRepository {
    override suspend fun getRandomFact(): Fact =
        ufFactsApi.getRandomFact().toDomain()
}