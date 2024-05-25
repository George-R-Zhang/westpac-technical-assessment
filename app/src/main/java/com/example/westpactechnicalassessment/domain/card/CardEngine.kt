package com.example.westpactechnicalassessment.domain.card

import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo
import com.example.westpactechnicalassessment.domain.msg.MessageManager
import com.example.westpactechnicalassessment.utility.JsonUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardEngine @Inject constructor(
    private val messageManager: MessageManager,
){

    suspend fun getCreditCards() {
        messageManager.get("$CREDIT_CARD?$SIZE=$REQUEST_SIZE")?.let { it ->
            JsonUtils.fromJsonString<List<CreditCardInfo>>(it)
        }
    }

    companion object {
        private const val CREDIT_CARD = "credit_cards"
        private const val SIZE = "size"

        private const val REQUEST_SIZE = 100
    }
}