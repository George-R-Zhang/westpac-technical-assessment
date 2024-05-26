package com.example.westpactechnicalassessment.ui.cards

import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo

sealed class CardScreenState {
    data object Initial : CardScreenState()
    data object Loading : CardScreenState()
    data class ShowCards (val cards : List<CreditCardInfo>) : CardScreenState()
    data object FailedToLoad : CardScreenState()
}