package com.mertoenjosh.questprovider.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.questprovider.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.R
import timber.log.Timber

@Composable
fun MySpinnerDropdown(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    preselected: String = "",
    list: List<String>,
    onSelectionChanged: (String)->Unit
) {
    val isOpen = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(preselected) }

    Box (modifier = modifier) {
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = selected.value,
                onValueChange = {},
                label = { Text(text = stringResource(title)) },
                trailingIcon = { Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = null)},
                readOnly = true
            )
            DropdownMenu(
                modifier = Modifier,
                expanded = isOpen.value,
                onDismissRequest = { isOpen.value = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selected.value = item
                            isOpen.value = false
                            onSelectionChanged(item)
                        },
                        content = {
                            Text(text = item, modifier = Modifier)
                        }
                    )
                }
            }
        }
        
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable {
                    isOpen.value = !isOpen.value
                }
        )
    }
}

@Preview(showBackground = true, name = "Spinner")
@Composable
fun MySpinnerDropdownPreview() {
    val names = listOf("Martin", "Holly", "Lynn", "Jansen")
    QuestProviderTheme {
        MySpinnerDropdown(
            title = R.string.category,
            list = names,
            preselected = names[0],
            onSelectionChanged = {selected ->
                Timber.tag("SPINNER").i("Selected: %s", selected)
            }
        )
    }
}