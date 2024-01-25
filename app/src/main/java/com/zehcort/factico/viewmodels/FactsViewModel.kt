package com.zehcort.factico.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zehcort.domain.usecases.GetRandomFact
import com.zehcort.domain.utils.Resource
import com.zehcort.factico.events.HomeEvents
import com.zehcort.factico.states.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val getRandomFact: GetRandomFact
) : ViewModel() {
    private val _state = mutableStateOf(HomeUiState())
    val state: State<HomeUiState> = _state

    fun fetchRandomFact() {
        viewModelScope.launch {
            getRandomFact().collect { result ->
                when (result) {
                    is Resource.Error -> onEvent(HomeEvents.OnError(result.message!!))

                    is Resource.Loading -> _state.value = state.value.copy(
                        isLoading = true
                    )

                    is Resource.Success -> onEvent(HomeEvents.OnFactLoaded(result.data!!))
                }
            }
        }
    }

    private fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnError -> _state.value = state.value.copy(
                isLoading = false,
                errorMessage = event.message
            )

            is HomeEvents.OnFactLoaded -> _state.value = state.value.copy(
                isLoading = false,
                currentFact = event.fact
            )
        }
    }
}