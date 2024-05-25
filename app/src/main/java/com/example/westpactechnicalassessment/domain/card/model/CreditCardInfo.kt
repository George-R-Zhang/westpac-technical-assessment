package com.example.westpactechnicalassessment.domain.card.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditCardInfo (
    val id: Int,
    val uid: String,
    @SerialName("credit_card_number")
    val cardNumber: String,
    @SerialName("credit_card_expiry_date")
    val expiryDate: String,
    @SerialName("credit_card_type")
    val cardType: String
)