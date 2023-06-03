package com.mertoenjosh.questprovider.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        color = MaterialTheme.colors.primary, strokeWidth = 5.dp
    )
}

@Preview(showBackground = true)
@Composable
fun ProgressBarPreview() {
    QuestProviderTheme {
        ProgressIndicator()
    }
}