package com.mertoenjosh.questprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.questprovider.navigation.SetupNavGraph
import com.mertoenjosh.questprovider.theme.QuestProviderTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("DUMMY_API: %s", BuildConfig.API_KEY)
        setContent {
            QuestProviderApp()
        }
    }
}

@Composable
fun QuestProviderApp() {
    QuestProviderTheme {
        val navHostController = rememberNavController()
        SetupNavGraph(navHostController = navHostController)
    }
}


