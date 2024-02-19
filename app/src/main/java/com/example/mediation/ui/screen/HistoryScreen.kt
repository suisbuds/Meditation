package com.example.mediation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediation.data.model.Message
import com.example.mediation.ui.theme.NunitoFontFamily
import com.example.mediation.ui.theme.background_color
import com.example.mediation.ui.theme.icon_color
import com.example.mediation.ui.theme.icon_dark_color

object Test {
    val messages = listOf(
        Message(id = 1, title = "1", time = "1", content = "test"),
        Message(id = 1, title = "1", time = "1", content = "testtesttesttesttesttesttesttesttest"),
        Message(id = 1, title = "1", time = "1", content = "testtesttesttesttesttesttesttesttesttesttesttesttesttest"),
        Message(id = 1, title = "1", time = "1", content = "testtesttesttesttesttesttesttesttesttesttesttesttesttest"),
        Message(id = 1, title = "1", time = "1", content = "testtesttesttesttesttesttesttesttesttesttesttesttesttest"),
        Message(id = 1, title = "1", time = "1", content = "testtesttesttesttesttesttesttesttesttesttesttesttesttest"),
        Message(id = 1, title = "1", time = "1", content = "testtesttesttesttesttesttesttesttesttesttesttesttesttest"),
    )
}

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit = {},
    messages: List<Message> = Test.messages
) {
    Surface(modifier = modifier.fillMaxSize(), color = background_color) {
        androidx.compose.material3.Scaffold(
            topBar = { TopBackHandlerBar(backToHome = { navigateToHome() }, topAppBarName = "历史留言") },
            bottomBar = { BottomNavigationBar() },
            containerColor = Color.Transparent
        ) { innerPadding ->
            LazyColumn(
                modifier = modifier.padding(innerPadding),
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(messages) { message -> HistoryCard(message = message) }
            }
        }
    }
}

@Composable
fun HistoryCard(message: Message, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = Color.White
    ) {
        Row(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .weight(1f).padding(start = 16.dp),
            ) {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = message.title,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = icon_dark_color,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = message.time,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                    fontStyle = FontStyle.Italic,
                    color = icon_color
                )
                Text(
                    text = message.content,
                    fontSize = 18.sp,
                    fontFamily = NunitoFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = icon_dark_color,
                )
                Spacer(modifier = modifier.height(4.dp))
            }
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

