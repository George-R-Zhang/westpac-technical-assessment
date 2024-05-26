package com.example.westpactechnicalassessment.ui.cards

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.westpactechnicalassessment.MainActivity
import com.example.westpactechnicalassessment.R
import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo
import com.example.westpactechnicalassessment.ui.theme.WestpacTechnicalAssessmentTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CreditCardScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var creditCardScreenVM: CreditCardScreenVM
    private lateinit var context: Context

    @Before
    fun init() {
        hiltRule.inject()
        creditCardScreenVM = MockCreditCardScreenVMFactory().makeCreditCardScreenVM()
        composeTestRule.activity.setContent {
            WestpacTechnicalAssessmentTheme {
                CreditCardsScreen(creditCardScreenVM)
            }
        }
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkIfCreditCardIsDisplayed() {
        composeTestRule.onNodeWithTag("TopBarTitle").assertIsDisplayed()
    }

    @Test
    fun checkIfLoadingScreenDisplayed() {
        runBlocking {
            creditCardScreenVM.setState(CreditCardScreenState.Loading)
        }
        composeTestRule.onNodeWithTag("LoadingView").assertIsDisplayed()
    }

    @Test
    fun checkIfFailedToLoadIsDisplayedCorrectly() {
        runBlocking {
            creditCardScreenVM.setState(CreditCardScreenState.FailedToLoad)
        }
        composeTestRule.onNodeWithText(context.getString(R.string.failed_to_load_credit_cards)).assertIsDisplayed()
    }

    @Test
    fun checkIfNoCreditCardIsDisplayedCorrectly() {
        runBlocking {
            creditCardScreenVM.setState(CreditCardScreenState.ShowCards(emptyList()))
        }
        composeTestRule.onNodeWithText(context.getString(R.string.no_credit_cards_found)).assertIsDisplayed()
    }

    @Test
    fun checkIfCreditCardListIsDisplayedCorrectly() {
        runBlocking {
            creditCardScreenVM.setState(CreditCardScreenState.ShowCards(creditCards))
        }
        val column = composeTestRule.onNodeWithTag("CreditCardsList")
        val card = column.onChildren()
        card.assertCountEquals(2)
    }

    companion object{
        private val creditCards = listOf(
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
    }
}