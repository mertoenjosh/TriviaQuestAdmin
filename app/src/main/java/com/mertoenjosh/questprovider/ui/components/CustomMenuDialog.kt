package com.mertoenjosh.questprovider.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme

@Composable
fun DialogTitle(
    icon: ImageVector,
    @StringRes title: Int,
    onClick: () -> Unit
) {
    // CLose btn and title
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable { onClick() },
            colorFilter = ColorFilter.tint(
                MaterialTheme.colors.onBackground
            )
        )

        Text(
            text = stringResource(title),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview(
    name = "dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 320
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DialogTitlePreview() {
    QuestProviderTheme {
        DialogTitle(
            icon = Icons.Default.Close,
            title = R.string.trivia_quest
        ){}
    }
}

@Composable
fun AccountImageAndEmail(
    name: String,
    email: String,
    onClick: () -> Unit
) {
    // Acc Image, Name email, Edit Account(chip)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(MaterialTheme.colors.background)
            .padding(bottom = 8.dp)
    ) {
        ProfileIcon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp, end = 10.dp),
            imageIcon = Icons.Filled.AccountCircle,
            imageColorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            size = 50.dp,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = name,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.onBackground
            )

            Text(
                text = email,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
@Preview(
    name = "dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 320
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun AccountImageAndEmailPreview() {
    QuestProviderTheme() {
        AccountImageAndEmail(
            name = "Martin Thuo",
            email = "mnthuo254@gmail.com"
        ){}
    }
}

@Composable
fun CustomDialogItem(
    icon: ImageVector,
    @StringRes label: Int,
    onClick: ()->Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(10.dp)
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = label),
            fontSize = 16.sp,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Preview(
    name = "dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 320
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun CustomDialogItemPreview() {
    QuestProviderTheme() {
        CustomDialogItem(
            icon = Icons.Default.Settings,
            label = R.string.settings,
            onClick = {}
        )
    }
}

@Composable
fun CustomMenuDialog(
    @StringRes title: Int,
    onDismiss: ()->Unit,
    name: String = "Martin Thuo",
    email: String = "martinthuo@gmail.com",
    onAccountImageAndEmailClicked: () -> Unit,
    onDialogItemClicked: () -> Unit,
    dialogTitleIcon: ImageVector = Icons.Default.Close
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 10.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                DialogTitle(title = title, icon = dialogTitleIcon, onClick = onDismiss)

                AccountImageAndEmail(
                    onClick = onAccountImageAndEmailClicked,
                    name = name,
                    email = email
                )

                // Line(-----)
                Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

                // Menu items: settings, logout
                CustomDialogItem(
                    icon = Icons.Filled.Settings,
                    label = R.string.settings,
                    onClick = onDialogItemClicked
                )
                CustomDialogItem(
                    icon = Icons.Filled.Settings,
                    label = R.string.logout,
                    onClick = onDialogItemClicked
                )
            }
        }
    }
}
@Preview(
    name = "dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 320
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun CustomMenuDialogPreview() {
    QuestProviderTheme() {
        CustomMenuDialog(
            title = R.string.trivia_quest,
            onDismiss = {},
            onAccountImageAndEmailClicked = {},
            onDialogItemClicked = {}
        )
    }
}