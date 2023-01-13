package com.mertoenjosh.triviaquestadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.screens.QuestionsScreen
import com.mertoenjosh.triviaquestadmin.ui.screens.WelcomeScreen
import com.mertoenjosh.triviaquestadmin.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            TriviaQuestAdminTheme {
                WelcomeScreen()
//                QuestionsScreen(viewModel)
            }
        }
    }
}

