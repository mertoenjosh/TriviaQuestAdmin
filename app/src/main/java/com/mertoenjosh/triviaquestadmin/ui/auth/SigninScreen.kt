package com.mertoenjosh.triviaquestadmin.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.navigation.Screen
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.components.*
import com.mertoenjosh.triviaquestadmin.viewmodel.AuthViewModel
import timber.log.Timber

@Composable
fun SignInScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    
    Scaffold (
        content = { paddingValues -> 
            SignInScreenContent(
                modifier = Modifier.padding(paddingValues),
                navHostController,
                authViewModel
            )
        }
    )
}

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    authViewModel: AuthViewModel
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        // Back icon
        MyIconButton(
            modifier = Modifier.align(Alignment.Start),
            color = Color.Black,
            onIconClicked = {
                navHostController.navigate(Screen.Welcome.route){
                    popUpTo(Screen.Welcome.route) {
                        inclusive = true
                    }
                }
            }
        )

        // Sign up heading
        HeadingText(
            text = R.string.sign_in,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        // Email
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            label = R.string.email,
            type = KeyboardType.Email
        ){
            Timber.tag("Input").d("InputEmail: %s", it)
            email.value = it
        }
        // Password
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            label = R.string.password,
            isPassword = true,
            type = KeyboardType.Password
        ) {
            Timber.tag("Input").d("Password: %s", it)
            password.value = it
        }

        // Btn
        MainActionButton(text = R.string.sign_in) {
            Timber.d("Fetching user------->")
            authViewModel.loginUser(email = email.value, password = password.value)
            // navHostController.navigate(route = Screen.Home.route)
            // authViewModel.errors.clear()
            Timber.d("Fetched------->")
        }
        // Google icon
        Image(
            painter = painterResource(id = R.drawable.google_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
        )
        // Already signed up?
        Row {
            SmallText(
                text = R.string.dont_have_an_account,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            SmallText(
                text = R.string.sign_up,
                link = true, modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .clickable {
                        navHostController.navigate(route = Screen.SignUp.route)
                    }
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Sign in screen"
)
@Composable
fun SignInScreenPreview() {
    TriviaQuestAdminTheme {
        SignInScreen(navHostController = rememberNavController())
    }
}