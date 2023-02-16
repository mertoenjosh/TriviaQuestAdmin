package com.mertoenjosh.questprovider.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.data.models.request.LoginRequest
import com.mertoenjosh.questprovider.navigation.Screen
import com.mertoenjosh.questprovider.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.ui.components.*
import com.mertoenjosh.questprovider.util.ScreenEvent
import com.mertoenjosh.questprovider.util.inputValidations.FocusedTextFieldKey
import com.mertoenjosh.questprovider.util.toast
import com.mertoenjosh.questprovider.viewmodel.AuthViewModel
import com.mertoenjosh.questprovider.viewmodel.CommonViewModel
import com.mertoenjosh.questprovider.viewmodel.InputValidationViewModel

@Composable
fun SignInScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
    inputValidationViewModel: InputValidationViewModel = hiltViewModel(),
    commonViewModel: CommonViewModel = hiltViewModel()
) {
    Scaffold (
        content = { paddingValues ->
            SignInScreenContent(
                modifier = Modifier.padding(paddingValues),
                navHostController,
                authViewModel,
                inputValidationViewModel,
                commonViewModel
            )
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    inputValidationViewModel: InputValidationViewModel,
    commonViewModel: CommonViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val events = remember(inputValidationViewModel.events, lifecycleOwner) {
        inputValidationViewModel.events.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val email by inputValidationViewModel.email.collectAsStateWithLifecycle()
    val password by inputValidationViewModel.password.collectAsStateWithLifecycle()
    val areInputsValid by inputValidationViewModel.areSignInInputsValid.collectAsStateWithLifecycle()
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val openDialog by commonViewModel.openDialog.collectAsState()

    authViewModel.loginLiveData.observe(lifecycleOwner) { data ->
        data?.let {
            commonViewModel.closeDialog()
            if (data.status == "success") {
                navHostController.navigate(Screen.Home.route)
            } else {
                data.message?.let { it1 -> context.toast(it1) }
            }
        }

    }

    LaunchedEffect(Unit) {
        events.collect{ event ->
            when(event) {
                is ScreenEvent.UpdateKeyboard -> {
                    if (event.show) keyboardController?.show() else keyboardController?.hide()
                }
                is ScreenEvent.ClearFocus -> focusManager.clearFocus()
                is ScreenEvent.RequestFocus -> {
                    when (event.textFieldKey) {
                        FocusedTextFieldKey.EMAIL -> emailFocusRequester.requestFocus()
                        FocusedTextFieldKey.PASSWORD -> passwordFocusRequester.requestFocus()
                        else -> {}
                    }
                }
                is ScreenEvent.MoveFocus -> focusManager.moveFocus(event.direction)
                is ScreenEvent.ShowToast -> context.toast(event.message)
                is ScreenEvent.Navigate -> navHostController.navigate(event.destination)

                else -> {}
            }
        }
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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

        if (openDialog) {
            DialogBoxLoading()
        }

        // Sign up heading
        HeadingText(
            text = R.string.sign_in,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        // Email
        MyOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(emailFocusRequester)
                .onFocusChanged { focusState ->
                    inputValidationViewModel.onTextFieldFocusChanged(
                        key = FocusedTextFieldKey.EMAIL,
                        isFocused = focusState.isFocused
                    )
                },
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
            inputWrapper = email,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            onValueChange = inputValidationViewModel::onEmailEntered,
            onImeKeyAction = inputValidationViewModel::onContinueClick
        )
        // Password
        MyOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester)
                .onFocusChanged { focusState ->
                    inputValidationViewModel.onTextFieldFocusChanged(
                        key = FocusedTextFieldKey.PASSWORD,
                        isFocused = focusState.isFocused
                    )
                },
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
            visualTransformation = PasswordVisualTransformation(),
            inputWrapper = password,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            onValueChange = inputValidationViewModel::onPasswordEntered,
            onImeKeyAction = inputValidationViewModel::onContinueClick
        )
        // Btn
        MainActionButton(
            text = R.string.sign_in,
            enabled = areInputsValid,
            onClick = {
                inputValidationViewModel.onContinueClick()
                commonViewModel.openDialog()
                val userLogin = LoginRequest(email = email.value, password = password.value)
                authViewModel.loginUser(userLogin)
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