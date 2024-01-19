package com.zehcort.factico.states

import com.zehcort.domain.models.Fact

data class FactsState(
    var isLoading: Boolean = true,
    var errorMessage: String? = null,
    var currentFact: Fact? = null
)