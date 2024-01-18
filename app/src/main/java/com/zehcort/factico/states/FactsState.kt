package com.zehcort.factico.states

import com.zehcort.domain.models.Fact

data class FactsState(
    var isLoading: Boolean = true,
    var currentFact: Fact? = null
)