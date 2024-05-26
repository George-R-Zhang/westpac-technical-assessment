package com.example.westpactechnicalassessment.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westpactechnicalassessment.usecase.IGetCreditCardsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class CreditCardScreenVM @Inject constructor(
    private val getCardsUC: IGetCreditCardsUC,
): ViewModel() {

    private val _stateFlow = MutableStateFlow<CreditCardScreenState>(CreditCardScreenState.Initial)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            setState(CreditCardScreenState.Loading)
            getCardsUC()?.let {
                setState(CreditCardScreenState.ShowCards(it))
            } ?: setState(CreditCardScreenState.FailedToLoad)
        }
    }

    fun loadMore() {
        val currentState = _stateFlow.value
        if (currentState !is CreditCardScreenState.ShowCards || currentState.isLoading || !currentState.canLoadMore) return

        viewModelScope.launch {
            setState(currentState.copy(isLoading = true))
            getCardsUC()?.let {
                val newList = currentState.creditCards + it
                setState(CreditCardScreenState.ShowCards(newList, isLoading = false))
            } ?: setState(currentState.copy(isLoading = false, canLoadMore = false))
        }
    }

    @VisibleForTesting
    suspend fun setState(state: CreditCardScreenState) {
        _stateFlow.emit(state)
    }
}