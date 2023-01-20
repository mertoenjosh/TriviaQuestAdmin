package com.mertoenjosh.triviaquestadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.triviaquestadmin.navigation.SetupNavGraph
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("DUMMY_API: %s", BuildConfig.API_KEY)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            QuestProviderApp()
        }
    }
}

@Composable
fun QuestProviderApp() {
    TriviaQuestAdminTheme {
        val navHostController = rememberNavController()
        SetupNavGraph(navHostController = navHostController)
    }
}


