package com.zehcort.factico.events

import com.zehcort.domain.models.Fact

sealed class HomeEvents {
    data class OnFactLoaded(val fact: Fact) : HomeEvents()
    data class OnError(val message: String) : HomeEvents()
}