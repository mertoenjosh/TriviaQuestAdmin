package com.mertoenjosh.triviaquestadmin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.components.*

@Composable
fun SignUpScreen() {
    Scaffold(
        content = { paddingValues ->
            SignUpScreenContent(modifier = Modifier.padding(paddingValues)) 
        }
    ) 
}

@Composable
fun SignUpScreenContent(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Back icon
        MyIconButton(
            modifier = Modifier.align(Alignment.Start),
            color = Color.Black,
            onIconClicked = { /* todo */ }
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
            )

            MyOutlinedTextField(
                modifier = Modifier.weight(.1f),
                label = R.string.last_name
            )
        }

        // Email
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            label = R.string.email,
            type = KeyboardType.Email
        )
        // Password
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            label = R.string.password,
            isPassword = true,
            type = KeyboardType.Password
        )
        // Confirm Password
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            label = R.string.confirm_password,
            isPassword = true,
            type = KeyboardType.Password
        )
        // Btn
        MainActionButton(text = R.string.sign_up, modifier = Modifier.padding(top = 48.dp)) {
            // TODO: onclick sign up

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
    TriviaQuestAdminTheme {
        SignUpScreen()
    }
}