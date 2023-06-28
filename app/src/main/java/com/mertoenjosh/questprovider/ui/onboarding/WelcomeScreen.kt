package com.mertoenjosh.questprovider.ui.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.navigation.Screen
import com.mertoenjosh.questprovider.ui.components.HeadingText
import com.mertoenjosh.questprovider.ui.components.MainActionButton
import com.mertoenjosh.questprovider.ui.components.SmallText
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    Scaffold(content = { paddingValues ->
        WelcomeScreenContent(modifier = Modifier.padding(paddingValues), navHostController)
    })
}

@Composable
fun WelcomeScreenContent(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        // Welcome heading
        HeadingText(
            text = R.string.welcome, modifier = Modifier.padding(top = 16.dp)
        )

        // Image slot
        AsyncImage(
            model = R.drawable.abc, modifier = Modifier.padding(8.dp), contentDescription = null
        )

        // terms of service
        SmallText(text = R.string.terms_of_service, modifier = Modifier.padding(8.dp))

        // sign up button
        MainActionButton(
            text = R.string.sign_up, modifier = Modifier.padding(top = 48.dp), enabled = true
        ) {
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
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            SmallText(text = R.string.sign_in,
                link = true,
                modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .clickable {
                        navHostController.navigate(Screen.SignIn.route)
                    })
        }
    }
}

@Preview(
    showBackground = true,
    name = "Welcome screen"
)
@Preview(
    name = "dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun WelcomeScreenPreview() {
    QuestProviderTheme {
        WelcomeScreen(
            navHostController = rememberNavController()
        )
    }
}