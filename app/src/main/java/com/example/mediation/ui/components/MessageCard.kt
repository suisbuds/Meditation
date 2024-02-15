package com.example.mediation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediation.R
import com.example.mediation.ui.theme.icon_dark_color
import com.example.mediation.ui.theme.message_icon_color

//alpha值：透明度
@Composable
fun MessageCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .alpha(0.6f)
            .clip(shape = RoundedCornerShape(12.dp)), shadowElevation = 8.dp
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Spacer(modifier = modifier.height(8.dp))
            CardHeader(onClose = {})
            /*Box(modifier = modifier
                .padding(8.dp)
                .fillMaxSize().background(Color.White)) {
                Surface(
                    modifier = modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(12.dp))
                ) {
                    CardContent()
                }
            }*/
        }
    }
}


@Composable
fun CardHeader(modifier: Modifier = Modifier, onClose: () -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            Text(
                text = "记录此次专注",
                fontWeight = FontWeight.SemiBold,
                color = message_icon_color,
                fontSize = 16.sp,
                modifier = modifier
                    .padding(start = 8.dp)
                    .alignByBaseline()
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close message",
                tint = message_icon_color,
                modifier = modifier
                    .alignByBaseline()
                    .clickable { onClose() })
        }
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = icon_dark_color,
                    unfocusedTextColor = icon_dark_color,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                textStyle = TextStyle(fontWeight = FontWeight.SemiBold),
                modifier = modifier
                    .weight(1f)
                    .border(width = 0.dp, color = Color.Transparent),
                label = { Text(text = "文案", color = icon_dark_color, fontWeight = FontWeight.SemiBold) }
            )
            Spacer(modifier = modifier.weight(1f))
        }
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
