package com.example.westpactechnicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.westpactechnicalassessment.ui.cards.CreditCardsScreen
import com.example.westpactechnicalassessment.ui.theme.WestpacTechnicalAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WestpacTechnicalAssessmentTheme {
                CreditCardsScreen()
            }
        }
    }
}