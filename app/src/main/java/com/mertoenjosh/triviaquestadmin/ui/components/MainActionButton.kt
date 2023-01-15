package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import java.util.*

@Composable
fun MainActionButton(@StringRes text: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ) {
       Text(
           text = stringResource(id = text).replaceFirstChar {
               if (it.isLowerCase()) it.titlecase(
                   locale = Locale.getDefault()
               ) else it.toString()
           },
           style = MaterialTheme.typography.button.copy(
               fontSize = 18.sp
           ),
           modifier = Modifier
               .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
       )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MainActionButtonPreview() {
    TriviaQuestAdminTheme {
        MainActionButton(text = R.string.sign_up) {
        }
    }
}