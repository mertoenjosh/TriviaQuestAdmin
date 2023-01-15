package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme

@Composable
fun MyOutlinedTextField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    leadingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    type: KeyboardType = KeyboardType.Text
) {
    val textValue = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }
    val primaryColor = MaterialTheme.colors.primary

    OutlinedTextField(
        modifier = modifier.padding(8.dp),
        value = textValue.value,
        onValueChange = {textValue.value = it },
        label = { Text(text = stringResource(label)) },
        leadingIcon = leadingIcon,
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        imageVector = if (showPassword.value)
                            Icons.Outlined.VisibilityOff
                        else
                            Icons.Outlined.Visibility,
                        contentDescription = if (showPassword.value)
                            stringResource(id = R.string.show_password)
                        else
                            stringResource(id = R.string.hide_password),
                    )
                }
            }
        } else null,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primaryColor,
            focusedLabelColor = primaryColor,
            leadingIconColor = primaryColor,
            cursorColor = primaryColor,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = type),
        visualTransformation = if (isPassword) {
            if (showPassword.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation()
        } else
            VisualTransformation.None
    )
}

@Preview(showBackground = true)
@Composable
fun MyTextFieldPreview() {
    TriviaQuestAdminTheme {
        MyOutlinedTextField(label = R.string.first_name)
    }
}

