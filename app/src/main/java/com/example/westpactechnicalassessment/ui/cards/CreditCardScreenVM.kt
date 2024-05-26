package com.example.westpactechnicalassessment.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westpactechnicalassessment.usecase.IGetCardsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditCardScreenVM @Inject constructor(
    private val getCardsUC: IGetCardsUC,
): ViewModel() {

    private val _stateFlow = MutableStateFlow<CreditCardScreenState>(CreditCardScreenState.Initial)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _stateFlow.emit(CreditCardScreenState.Loading)
            getCardsUC()?.let {
                _stateFlow.emit(CreditCardScreenState.ShowCards(it))
            } ?: _stateFlow.emit(CreditCardScreenState.FailedToLoad)
        }
    }

    fun loadMore() {
        val currentState = _stateFlow.value
        if (currentState !is CreditCardScreenState.ShowCards || currentState.isLoading || !currentState.canLoadMore) return

        viewModelScope.launch {
            _stateFlow.emit(currentState.copy(isLoading = true))
            getCardsUC()?.let {
                val newList = currentState.creditCards + it
                _stateFlow.emit(CreditCardScreenState.ShowCards(newList, isLoading = false))
            } ?: _stateFlow.emit(currentState.copy(isLoading = false, canLoadMore = false))
        }
    }
}