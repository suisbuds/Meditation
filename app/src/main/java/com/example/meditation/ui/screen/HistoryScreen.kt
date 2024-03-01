package com.example.meditation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.data.model.Message
import com.example.meditation.ui.theme.*
import com.example.meditation.ui.viewmodel.HomeViewModel

/*
* Bug Fix:provide latest item and update it,as well as unique id
* */

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    homeViewModel: HomeViewModel,
    colorIndex: Int = 0,
    animationDuration: Long = 300
) {
    LaunchedEffect(Unit) {
        homeViewModel.getAllMessages()
    }
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
            val historyMessages by homeViewModel.historyMessages.collectAsState()
            LazyColumn(
                modifier = modifier.padding(innerPadding),
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = historyMessages, key = { message -> message.id }) { message ->
                    val currentMessage by rememberUpdatedState(newValue = message)
                    SwipeDeleteWrapper(
                        homeViewModel = homeViewModel,
                        currentMessage = currentMessage,
                        animationDuration = animationDuration
                    ) {
                        HistoryCard(it)
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
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { isExpanded = !isExpanded },
        backgroundColor = Color.White
    ) {
        Column(modifier = modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 15.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = message.title,
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = message_icon_color,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                    modifier = modifier.padding(start = 8.dp)
                )

            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
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
            if (isExpanded) {
                Text(
                    text = message.content,
                    fontSize = 12.sp,
                    fontFamily = NunitoFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = icon_dark_color_brown,
                    modifier = modifier.padding(8.dp),
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeDeleteWrapper(
    homeViewModel: HomeViewModel,
    currentMessage: Message,
    animationDuration: Long,
    content: @Composable (Message) -> Unit,
) {

    val state = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                homeViewModel.swipeToDeleteMessage(currentMessage.id)
                true
            } else false
        },
        positionalThreshold = { 600f }
    )
    AnimatedVisibility(
        visible = !state.isDismissed(DismissDirection.EndToStart),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration.toInt()),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = { DeleteBackground(swipeDismissState = state) },
            dismissContent = { content(currentMessage) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(modifier: Modifier = Modifier, swipeDismissState: DismissState) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else {
        Color.Transparent
    }
    val scale by animateFloatAsState(targetValue = if (swipeDismissState.targetValue == DismissValue.Default) 1f else 1.25f)
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
                modifier = modifier
                    .padding(16.dp)
                    .scale(scale)
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
                    "Abssssssssssssssaaaaaaaaaaaa",
                    "2024-2-28"
                )
            )
        }
    }
}
