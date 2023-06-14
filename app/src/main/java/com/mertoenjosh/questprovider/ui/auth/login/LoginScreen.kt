package com.mertoenjosh.questprovider.ui.auth.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.navigation.Screen
import com.mertoenjosh.questprovider.ui.components.DialogBoxLoading
import com.mertoenjosh.questprovider.ui.components.HeadingText
import com.mertoenjosh.questprovider.ui.components.MainActionButton
import com.mertoenjosh.questprovider.ui.components.MyIconButton
import com.mertoenjosh.questprovider.ui.components.MyOutlinedTextField
import com.mertoenjosh.questprovider.ui.components.SmallText
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.ui.util.UiState
import com.mertoenjosh.questprovider.util.toast

@Composable
fun SignInScreen(
    navHostController: NavHostController,
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val loginState = loginViewModel.loginState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        content = { paddingValues ->
            SignInScreenContent(
                modifier = Modifier.padding(paddingValues),
                context = context,
                navHostController,
                loginState = loginState.value,
                onEmailChange = { email ->
                    loginViewModel.handleLoginEvents(LoginEvents.EmailChanged(email))
                },
                onPasswordChanged = { password ->
                    loginViewModel.handleLoginEvents(LoginEvents.PasswordChanged(password))
                },
                onLogin = { loginEvent ->
                    loginViewModel.handleLoginEvents(loginEvent)
                }
            )
        }
    )
}

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    context: Context,
    navHostController: NavHostController,
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLogin: (LoginEvents) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (loginState.uiState) {
            is UiState.Loading -> {
                DialogBoxLoading()
            }

            is UiState.Success -> {
                LaunchedEffect(key1 = Unit) {
                    navHostController.navigate(route = Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            }

            else -> {
                LaunchedEffect(key1 = loginState.uiState?.error) {
                    loginState.uiState?.error?.let { context.toast(it) }
                }
            }
        }
        // Back icon
        MyIconButton(
            modifier = Modifier.align(Alignment.Start),
            color = MaterialTheme.colors.primary,
            onIconClicked = {
                navHostController.navigate(Screen.Welcome.route) {
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
            modifier = Modifier
                .fillMaxWidth(),
            value = loginState.email,
            label = R.string.email,
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            onValueChange = { onEmailChange(it) }
        )
        // Password
        MyOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = loginState.password,
            label = R.string.password,
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            isPassword = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            onValueChange = { onPasswordChanged(it) },
        )
        // Btn
        MainActionButton(
            text = R.string.sign_in,
            enabled = true,
            onClick = {
                onLogin(LoginEvents.LoginClicked)
            }
        )

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
    QuestProviderTheme {
        SignInScreen(navHostController = rememberNavController())
    }
}