package com.mertoenjosh.questprovider.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.util.capitalize

@Composable
fun MyRadioGroup(
    modifier: Modifier = Modifier,
    options: Array<String> = arrayOf("Easy","Medium","Hard"),
    selected: String = options.first(),
) {
    val selectedOption  = remember {
        mutableStateOf(selected.capitalize())
    }
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { text ->
            val isSelected = text == selectedOption.value
            val colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.primary,
                disabledColor = Color.LightGray
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = (text == selectedOption.value),
                    onClick = {}
                )
            ) {
                RadioButton(
                    selected = isSelected,
                    colors = colors,
                    onClick = {
                        selectedOption.value = text
                    }
                )

                Text(text = text, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyRadioGroupPreview() {
    QuestProviderTheme {
        MyRadioGroup()
    }
}