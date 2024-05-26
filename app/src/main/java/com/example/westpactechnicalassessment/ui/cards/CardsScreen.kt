package com.example.westpactechnicalassessment.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.westpactechnicalassessment.R
import com.example.westpactechnicalassessment.domain.card.model.CreditCardInfo
import com.example.westpactechnicalassessment.ui.common.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(
    viewModel: CardScreenVM = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.requiredHeight(45.dp),
                title = { Text(text = "Westpac Technical Assessment") },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            CardsScreenView(state)
        }
    }
}

@Composable
fun CardsScreenView(state: CardScreenState) {

    when (state) {
        is CardScreenState.Loading -> {
            LoadingView()
        }

        is CardScreenState.ShowCards -> {
            if (state.cards.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_credit_cards_found),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    state = rememberLazyListState()
                ) {
                    state.cards.forEach {
                        item {
                            Card(
                                cardType = it.cardType,
                                cardNumber = it.cardNumber,
                                expiryDate = it.expiryDate
                            )
                        }
                    }
                }
            }
        }

        is CardScreenState.FailedToLoad -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    text = stringResource(id = R.string.failed_to_load_credit_cards),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        else -> Unit
    }
}

@Composable
fun Card(
    cardType: String,
    cardNumber: String,
    expiryDate: String,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = cardType,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = cardNumber,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = expiryDate,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowCardsPreview() {
    CardsScreenView(
        CardScreenState.ShowCards(
            listOf(
                CreditCardInfo(
                    9487,
                    "479e48f8-b2a6-449a-bc07-8c1df6097d41",
                    "Visa",
                    "1234-5678-9012-3456",
                    "2029-12-31"
                ),
            )
        )
    )
}

@Composable
@Preview(showBackground = true)
fun EmptyCardsPreview() {
    CardsScreenView(CardScreenState.ShowCards(emptyList()))
}

@Composable
@Preview(showBackground = true)
fun LoadingPreview() {
    CardsScreenView(CardScreenState.Loading)
}

@Composable
@Preview
fun CardViewPreview() {
    Card("Visa", "1234-5678-9012-3456", "2029-12-31")
}
