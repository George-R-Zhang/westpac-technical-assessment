package com.example.westpactechnicalassessment.ui.cards

import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo

sealed interface CreditCardScreenState {
    data object Initial : CreditCardScreenState
    data object Loading : CreditCardScreenState
    data class ShowCards (val creditCards : List<CreditCardInfo>, val isLoading: Boolean = false, val canLoadMore: Boolean = true) : CreditCardScreenState
    data object FailedToLoad : CreditCardScreenState
}