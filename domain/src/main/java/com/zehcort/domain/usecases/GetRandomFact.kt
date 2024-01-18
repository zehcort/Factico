package com.zehcort.domain.usecases

import com.zehcort.domain.models.Fact
import com.zehcort.domain.repositories.FactsRepository
import com.zehcort.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRandomFact @Inject constructor(
    private val repository: FactsRepository
) {
    suspend operator fun invoke(): Flow<Resource<Fact>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getRandomFact()
            emit(Resource.Success(response))
        } catch (ex: Exception) {
            emit(Resource.Error(message = ex.message!!, stacktrace = ex.stackTrace))
        }
    }
}