package com.zehcort.domain.repositories

import com.zehcort.domain.models.Fact

interface FactsRepository {
    suspend fun getRandomFact(): Fact
}