package com.mertoenjosh.questprovider.ui.auth

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
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.navigation.Screen
import com.mertoenjosh.questprovider.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.ui.components.*
import com.mertoenjosh.questprovider.viewmodel.AuthViewModel
import timber.log.Timber

@Composable
fun SignUpScreen(navHostController: NavHostController) {
    Scaffold(
        content = { paddingValues ->
            SignUpScreenContent(
                modifier = Modifier.padding(paddingValues),
                navHostController,
                authViewModel = hiltViewModel()
            )
        }
    ) 
}

@Composable
fun SignUpScreenContent(
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
        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }


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
            text = R.string.sign_up,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        // First Name, Second Name
        Row {
            MyOutlinedTextField(
                modifier = Modifier.weight(.1f),
                label = R.string.first_name
            ){
                Timber.d("FirstName: %s", it)
                firstName.value = it
            }

            MyOutlinedTextField(
                modifier = Modifier.weight(.1f),
                label = R.string.last_name
            ){
                Timber.d("LastName: %s", it)
                lastName.value = it
            }
        }

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
            Timber.d("InputEmail: %s", it)
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
        ){
            Timber.d("Password: %s", it)
            password.value = it
        }
        // Confirm Password
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
            label = R.string.confirm_password,
            isPassword = true,
            type = KeyboardType.Password
        ){
            Timber.tag("Input").d("Confirm password: %s", it)
            confirmPassword.value = it
        }
        // Btn
        MainActionButton(text = R.string.sign_up) {
            if (
                authViewModel.registerUser(
                    firstName = firstName.value,
                    lastName = lastName.value, email = email.value,
                    password = password.value,
                    confirmPassword = confirmPassword.value)
            ) {
                navHostController.navigate(route = Screen.Home.route)
            } else {
                Timber.e("Signup Errors: %s", authViewModel.errors)
            }
            authViewModel.errors.clear()
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