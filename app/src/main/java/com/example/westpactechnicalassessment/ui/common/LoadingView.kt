package com.example.westpactechnicalassessment.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingView(indicatorSize: Dp = 64.dp) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator(
            modifier = Modifier.width(indicatorSize).testTag("LoadingView"),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surface,
        )
    }

}

@Preview(widthDp = 360, heightDp = 720)
@Composable
fun LoadingViewPreview() {
    LoadingView()
}
