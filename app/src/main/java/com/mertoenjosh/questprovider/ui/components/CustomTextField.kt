package com.mertoenjosh.questprovider.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.util.OnImeKeyAction
import com.mertoenjosh.questprovider.util.OnValueChange
import com.mertoenjosh.questprovider.util.inputValidations.InputWrapper

@Composable
fun MyOutlinedTextField(
    modifier: Modifier = Modifier,
    inputWrapper: InputWrapper,
    @StringRes label: Int,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = remember {
        KeyboardOptions.Default
    },
    visualTransformation: VisualTransformation = remember {
        VisualTransformation.None
    },
    isPassword: Boolean = false,
    onValueChange: OnValueChange,
    onImeKeyAction: OnImeKeyAction
) {
    val fieldValue = remember {
        mutableStateOf(TextFieldValue(inputWrapper.value, TextRange(inputWrapper.value.length)))
    }

    val showPassword = remember {
        mutableStateOf(false)
    }
    val primaryColor = MaterialTheme.colors.primary

    Column(
        modifier = modifier.padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fieldValue.value,
            onValueChange = {
                fieldValue.value = it
                onValueChange(it.text)
            },
            label = { Text(text = stringResource(label)) },
            isError = inputWrapper.errorId != null,
            leadingIcon = leadingIcon,
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(
                            imageVector = if (showPassword.value) {
                                Icons.Outlined.VisibilityOff
                            } else {
                                Icons.Outlined.Visibility
                            },
                            contentDescription = if (showPassword.value)
                                stringResource(id = R.string.show_password)
                            else
                                stringResource(id = R.string.hide_password),
                        )
                    }
                }
            } else trailingIcon,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primaryColor,
                focusedLabelColor = primaryColor,
                leadingIconColor = primaryColor,
                cursorColor = primaryColor,
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = remember {
                KeyboardActions(onAny = { onImeKeyAction() })
            },
            visualTransformation = if (isPassword) {
                if (showPassword.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()
            } else visualTransformation
        )

        if (inputWrapper.errorId != null) {
            Text(
                text = stringResource(inputWrapper.errorId),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyOutlinedTextFieldPreview() {
    QuestProviderTheme {
        MyOutlinedTextField(
            label = R.string.first_name,
            inputWrapper = InputWrapper(),
            onValueChange = {},
            onImeKeyAction = {}
        )
    }
}