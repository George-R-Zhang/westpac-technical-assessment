package com.example.westpactechnicalassessment.utils

import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo
import com.example.westpactechnicalassessment.utility.JsonUtils
import org.junit.Assert
import org.junit.Test

class JsonUtilsTest {

    @Test
    fun `Given empty json string should return null`() {
        val jsonString = ""
        val actual = JsonUtils.fromJsonString<CreditCardInfo>(jsonString)
        Assert.assertNull(actual)
    }

    @Test
    fun `Given valid json string should return expected data`() {
        val expect = listOf(
            CreditCardInfo(
                id = 4021,
                uid = "eb0a2da8-0343-47b8-82d4-f669afa05498",
                cardNumber = "1228-1221-1221-1431",
                expiryDate = "2028-05-25",
                cardType = "maestro"
            ),
            CreditCardInfo(
                id = 5716,
                uid = "3b77c54f-907e-4585-8aed-241d5f77a9c2",
                cardNumber = "1228-1221-1221-1431",
                expiryDate = "2026-05-26",
                cardType = "american_express"
            ),
        )
        val actual = JsonUtils.fromJsonString<List<CreditCardInfo>>(jsonString)

        Assert.assertEquals(expect, actual)
    }

    companion object{
        private val jsonString = """
            [
              {
                "id": 4021,
                "uid": "eb0a2da8-0343-47b8-82d4-f669afa05498",
                "credit_card_number": "1228-1221-1221-1431",
                "credit_card_expiry_date": "2028-05-25",
                "credit_card_type": "maestro"
              },
              {
                "id": 5716,
                "uid": "3b77c54f-907e-4585-8aed-241d5f77a9c2",
                "credit_card_number": "1228-1221-1221-1431",
                "credit_card_expiry_date": "2026-05-26",
                "credit_card_type": "american_express"
              }
            ]
        """.trimIndent()
    }
}