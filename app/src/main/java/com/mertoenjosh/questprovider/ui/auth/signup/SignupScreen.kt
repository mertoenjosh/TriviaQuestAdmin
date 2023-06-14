package com.mertoenjosh.questprovider.ui.auth.signup

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.navigation.Screen
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
fun SignUpScreen(navHostController: NavHostController) {
    val signupViewModel: SignupViewModel = hiltViewModel()
    val signupState = signupViewModel.signupState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        content = { paddingValues ->
            SignUpScreenContent(
                modifier = Modifier.padding(paddingValues),
                navHostController = navHostController,
                context = context,
                signupState = signupState.value,
                onFirstNameChanged = { firstName ->
                    signupViewModel.handleSignupEvents(SignupEvents.FirstNameChanged(firstName))
                },
                onLastNameChanged = { lastName ->
                    signupViewModel.handleSignupEvents(SignupEvents.LastNameChanged(lastName))
                },
                onEmailChanged = { email ->
                    signupViewModel.handleSignupEvents(SignupEvents.EmailChanged(email))
                },
                onPasswordNameChanged = { password ->
                    signupViewModel.handleSignupEvents(SignupEvents.PasswordChanged(password))
                },
                onPasswordConfirmNameChanged = { passwordConfirm ->
                    signupViewModel.handleSignupEvents(
                        SignupEvents.ConfirmPasswordChanged(
                            passwordConfirm
                        )
                    )
                },
                onSignup = { signupEvent ->
                    signupViewModel.handleSignupEvents(signupEvent)
                }
            )
        }
    )
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    context: Context,
    signupState: SignupState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordNameChanged: (String) -> Unit,
    onPasswordConfirmNameChanged: (String) -> Unit,
    onSignup: (SignupEvents.SignupClicked) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (signupState.uiState) {
            is UiState.Loading -> {
                DialogBoxLoading()
            }

            is UiState.Success -> {
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }

            else -> {
                LaunchedEffect(key1 = signupState.uiState?.error) {
                    signupState.uiState?.error?.let { context.toast(it) }
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
            text = R.string.sign_up,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        // First Name, Second Name
        Row {
            MyOutlinedTextField(
                modifier = Modifier.weight(.1f),
                value = signupState.firstName,
                label = R.string.first_name,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                onValueChange = { onFirstNameChanged(it) },
            )

            MyOutlinedTextField(
                modifier = Modifier.weight(.1f),
                value = signupState.lastName,
                label = R.string.last_name,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                onValueChange = { onLastNameChanged(it) },
            )

        }

        // Email
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signupState.email,
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
            onValueChange = { onEmailChanged(it) },
        )
        // Password
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signupState.password,
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            onValueChange = { onPasswordNameChanged(it) },
        )
        // Confirm Password
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signupState.confirmPassword,
            label = R.string.confirm_password,
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            onValueChange = { onPasswordConfirmNameChanged(it) },
        )
        // Btn
        MainActionButton(
            text = R.string.sign_up,
            enabled = true,
            onClick = { onSignup(SignupEvents.SignupClicked) }
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
                        navHostController.navigate(route = Screen.SignIn.route)
                    }
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Sign up screen"
)
@Composable
fun SignUpScreenPreview() {
    QuestProviderTheme {
        SignUpScreen(navHostController = rememberNavController())
    }
}