package com.example.mediation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediation.R
import com.example.mediation.ui.theme.icon_dark_color

//alpha值：透明度
@Composable
fun MessageCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .alpha(0.5f), contentAlignment = Alignment.Center
    ) {
    }
}

@Preview
@Composable
fun CardHeader(modifier: Modifier = Modifier, onClose: () -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row {
            Text(text = "记录此次专注", fontWeight = FontWeight.SemiBold, color = icon_dark_color)
            IconButton(onClick = { onClose() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close message", tint = icon_dark_color)
            }
        }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = icon_dark_color,
                unfocusedTextColor = icon_dark_color,
            ), singleLine = true, textStyle = TextStyle(fontWeight = FontWeight.SemiBold)
        )
    }
}


@Composable
fun CardContent(modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.fillMaxHeight()) {
        OutlinedTextField(value = text, onValueChange = { text = it })
        Row(modifier = modifier.align(Alignment.End)) {
            IconButton(onClick = {
            }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.horiz_icon), contentDescription = "more")
            }
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "share")
            }
        }
    }
}
