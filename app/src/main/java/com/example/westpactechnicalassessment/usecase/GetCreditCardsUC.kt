package com.example.westpactechnicalassessment.usecase

import com.example.westpactechnicalassessment.domain.card.CardEngine
import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo
import javax.inject.Inject

interface IGetCreditCardsUC {
    suspend operator fun invoke(): List<CreditCardInfo>?
}

class GetCreditCardsUC @Inject constructor(
    private val cardEngine: CardEngine
): IGetCreditCardsUC {
    override suspend operator fun invoke() = cardEngine.getCreditCards()
}