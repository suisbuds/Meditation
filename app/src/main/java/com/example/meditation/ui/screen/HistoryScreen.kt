package com.example.meditation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.data.model.Message
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.background_color
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown
import com.example.meditation.ui.theme.message_icon_color
import com.example.meditation.ui.viewmodel.HomeViewModel


@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    messages: List<Message>,
    homeViewModel: HomeViewModel,
    colorIndex: Int
) {
    Surface(modifier = modifier.fillMaxSize(), color = background_color) {
        Scaffold(
            topBar = {
                TopBackHandlerBar(
                    backToHome = { backToHome() },
                    topAppBarName = "历史留言"
                )
            },
            bottomBar = { BottomNavigationBar(colorIndex = colorIndex) },
            containerColor = Color.Transparent
        ) { innerPadding ->
            LazyColumn(
                modifier = modifier.padding(innerPadding),
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(messages) { message ->
                    SwipeDeleteWrapper(homeViewModel = homeViewModel, message = message) {
                        HistoryCard(message = message)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryCard(message: Message, modifier: Modifier = Modifier) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded },
        backgroundColor = Color.White
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Spacer(modifier = modifier.height(8.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = message.title,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    color = message_icon_color,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                    modifier = modifier.padding(start = 8.dp)
                )
                Text(
                    text = message.time,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                    fontStyle = FontStyle.Italic,
                    color = icon_color_brown,
                    modifier = modifier.padding(end = 8.dp)
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            Row {
                Text(
                    text = message.content,
                    fontSize = 12.sp,
                    fontFamily = NunitoFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = icon_dark_color_brown,
                    modifier = modifier.padding(8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
                Spacer(modifier = modifier.height(8.dp))
            }
            Spacer(modifier = modifier.width(5.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeDeleteWrapper(
    homeViewModel: HomeViewModel,
    animationDuration: Int = 3000,
    message: Message,
    content: @Composable () -> Unit,
) {
    var isRemove by remember {
        mutableStateOf(false)
    }
    val state = rememberDismissState(
        confirmValueChange = { value: DismissValue ->
            when (value) {
                DismissValue.DismissedToStart -> {
                    isRemove = true
                    true
                }

                else -> false
            }
        }
    )
    LaunchedEffect(key1 = isRemove) {
        if (isRemove) {
            homeViewModel.swipeToDeleteMessage(message)
        }
    }
    AnimatedVisibility(
        visible = !isRemove, exit = shrinkVertically(
            animationSpec = tween(animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = { DeleteBackground(swipeDismissState = state) },
            dismissContent = { content() }, directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(modifier: Modifier = Modifier, swipeDismissState: DismissState) {
    val color = when (swipeDismissState.dismissDirection) {
        DismissDirection.EndToStart -> {
            Color.Red
        }

        else -> {
            Color.Transparent
        }
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = color
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                tint = Color.White,
                modifier = modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun HistoryCardPreview() {
    MeditationTheme {
        Surface {
            HistoryCard(
                message = Message(
                    1,
                    "abcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "Abc",
                    "2024-2-28"
                )
            )
        }
    }
}
