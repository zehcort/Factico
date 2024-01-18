package com.zehcort.factico.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zehcort.domain.usecases.GetRandomFact
import com.zehcort.domain.utils.Resource
import com.zehcort.factico.states.FactsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val getRandomFact: GetRandomFact
) : ViewModel() {
    private val _state = mutableStateOf(FactsState())
    val state: State<FactsState> = _state

    fun fetchRandomFact() {
        viewModelScope.launch {
            getRandomFact().collect { result ->
                when (result) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> _state.value = state.value.copy(
                        isLoading = true
                    )

                    is Resource.Success -> _state.value = state.value.copy(
                        isLoading = false,
                        currentFact = result.data!!
                    )
                }
            }
        }
    }
}