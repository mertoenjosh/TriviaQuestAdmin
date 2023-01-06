package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialogItem(
    icon: ImageVector,
    label: String,
    onClick: ()->Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Menu Item Icon",
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomMenuDialog(title: String = "Trivia Quest", onDismiss: ()->Unit) {
    Dialog(onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 10.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // CLose btn and title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Image(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close details menu dialog",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { onDismiss() }
                    )
                    
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                    )
                }

                // Acc Image, Name email, Edit Account(chip)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    ProfileIcon(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 10.dp, end = 10.dp),
                        imageIcon = Icons.Filled.AccountCircle,
                        imageColorFilter = ColorFilter.tint(Color.DarkGray),
                        contentDescription = "Account Icon",
                        size = 50.dp,
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ){
                        Text(
                            text = "Martin Thuo",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "mnthuo254@gmail.com",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                // TODO: Create edit account chip

                // Line(-----)
                Divider(color = Color.LightGray, thickness = 1.dp)

                // Menu items: settings, logout
                CustomDialogItem(icon = Icons.Filled.Settings, label = "Server Stats", onClick = { })
                CustomDialogItem(icon = Icons.Filled.Settings, label = "Settings", onClick = { })
                CustomDialogItem(icon = Icons.Filled.Settings, label = "Logout", onClick = { })
            }

        }

    }
}