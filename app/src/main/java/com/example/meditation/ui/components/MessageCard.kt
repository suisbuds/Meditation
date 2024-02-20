package com.example.meditation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediation.R
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color
import com.example.meditation.ui.theme.icon_dark_color
import com.example.meditation.ui.viewmodel.HomeViewModel
import java.time.LocalDate

//alpha值：透明度


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    onShare: () -> Unit = {},
    navigateToHistory: () -> Unit = {},
    onClose: () -> Unit,
    homeViewModel: HomeViewModel
) {

    Surface(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 96.dp)
            .clip(shape = RoundedCornerShape(12.dp)), color = Color.White.copy(alpha = 0.6f)
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            CardHeader(onClose = onClose, homeViewModel = homeViewModel)
            CardContent(
                onShare = onShare,
                navigateToHistory = navigateToHistory,
                homeViewModel = homeViewModel
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardHeader(modifier: Modifier = Modifier, onClose: () -> Unit, homeViewModel: HomeViewModel) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(100.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            Text(
                text = "记录此次专注",
                fontFamily = NunitoFontFamily,
                color = icon_dark_color,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = modifier
                    .padding(start = 8.dp)
                    .alignByBaseline()
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "close message",
                tint = icon_dark_color,
                modifier = modifier
                    .alignByBaseline()
                    .size(24.dp)
                    .clickable { onClose() })
        }
        Row(
            modifier = modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.weight(1f)
            ) {
                TextField(
                    value = text,
                    onValueChange = {  text=it;homeViewModel.updateTitle(input = it) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = icon_dark_color,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontFamily = NunitoFontFamily,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        color = icon_dark_color,
                        fontSize = 16.sp
                    ),
                    modifier = modifier
                        .border(width = 0.dp, color = Color.Transparent)
                        .offset(x = (-8).dp, y = (-4).dp),
                    placeholder = {
                        AnimatedVisibility(visible = text == "") {
                            Text(
                                text = "标题",
                                color = icon_dark_color,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = NunitoFontFamily,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp,
                            )
                        }
                    },
                    singleLine = true,
                )
                Text(
                    text = "日期：" + LocalDate.now(),
                    fontStyle = FontStyle.Italic,
                    fontFamily = NunitoFontFamily,
                    color = icon_color,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                        .offset(y = (-8).dp)
                )

            }
            Spacer(modifier = modifier.weight(1f))
        }
    }
}


@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    onShare: () -> Unit,
    navigateToHistory: () -> Unit,
    homeViewModel: HomeViewModel
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 8.dp),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text=it;homeViewModel.updateContent(it) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = icon_dark_color,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontFamily = NunitoFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = icon_dark_color
                    ),
                    modifier = modifier
                        .border(width = 0.dp, color = Color.Transparent),
                    placeholder = {
                        AnimatedVisibility(visible = text == "") {
                            Text(
                                text = "留言",
                                color = icon_dark_color,
                                fontWeight = FontWeight.Medium,
                                fontFamily = NunitoFontFamily,
                            )
                        }
                    },
                )
                Spacer(modifier = modifier.weight(1f))
            }
        }
        Spacer(modifier = modifier.height(4.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 8.dp), horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.horiz_icon),
                contentDescription = "more",
                modifier = modifier
                    .size(24.dp)
                    .clickable { navigateToHistory() },
                tint = icon_dark_color
            )
            Spacer(modifier = modifier.width(16.dp))
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "share",
                modifier = modifier
                    .size(24.dp)
                    .clickable { onShare() },
                tint = icon_dark_color
            )
        }
    }
}

