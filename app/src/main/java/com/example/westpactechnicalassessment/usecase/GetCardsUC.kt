package com.example.westpactechnicalassessment.usecase

import com.example.westpactechnicalassessment.domain.card.CardEngine
import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo
import javax.inject.Inject

interface IGetCardsUC {
    suspend operator fun invoke(): List<CreditCardInfo>?
}

class GetCardsUC @Inject constructor(
    private val cardEngine: CardEngine
): IGetCardsUC {
    override suspend operator fun invoke() = cardEngine.getCreditCards()
}