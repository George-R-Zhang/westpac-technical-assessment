package com.example.westpactechnicalassessment.ui.cards

import com.example.westpactechnicalassessment.usecase.IGetCreditCardsUC
import org.mockito.Mockito

class MockCreditCardScreenVMFactory {
    fun makeCreditCardScreenVM() : CreditCardScreenVM {
        val mockGetCreditCardsUC = Mockito.mock(IGetCreditCardsUC::class.java)
        return CreditCardScreenVM(mockGetCreditCardsUC)
    }
}