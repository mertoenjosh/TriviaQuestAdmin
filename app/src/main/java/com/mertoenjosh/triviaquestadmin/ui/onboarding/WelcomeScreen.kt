package com.mertoenjosh.triviaquestadmin.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.navigation.Screen
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.components.HeadingText
import com.mertoenjosh.triviaquestadmin.ui.components.MainActionButton
import com.mertoenjosh.triviaquestadmin.ui.components.SmallText

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    Scaffold (
        content = { paddingValues ->
            WelcomeScreenContent(modifier = Modifier.padding(paddingValues), navHostController)
        }
    )
}

@Composable
fun WelcomeScreenContent(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        // Welcome heading
        HeadingText(
            text = R.string.welcome,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        // Image slot
        Image(
            painter = painterResource(R.drawable.abc),
            modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
        
        // terms of service
        SmallText(text = R.string.terms_of_service, modifier = Modifier.padding(8.dp))

        // sign up button
        MainActionButton(text = R.string.sign_up, modifier = Modifier.padding(top = 48.dp)) {
            // TODO: Sign up first
            navHostController.navigate(route = Screen.SignUp.route)
        }

        // google icon
        Image(
            painter = painterResource(id = R.drawable.google_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
        )
        // already signed up? text
        Row {
            SmallText(
                text = R.string.already_have_an_account,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            SmallText(
                text = R.string.sign_in,
                link = true, modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .clickable {
                        navHostController.navigate(Screen.SignIn.route)
                    }
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Welcome screen"
)
@Composable
fun WelcomeScreenPreview() {
    TriviaQuestAdminTheme {
        WelcomeScreen(
            navHostController = rememberNavController()
        )
    }
}