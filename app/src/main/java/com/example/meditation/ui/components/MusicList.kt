package com.example.meditation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediation.R
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.icon_dark_color

@Composable
fun MusicList(
    modifier: Modifier = Modifier,
    musicList: List<Music>,
    onClick: (Music) -> Unit
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .height(270.dp)
            .padding(15.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        LazyColumn(modifier = modifier.padding(8.dp)) {
            item {
                Spacer(modifier = modifier.height(10.dp))
                MusicListHeader(modifier = modifier)
                Spacer(modifier = modifier.height(8.dp))
            }
            item {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.height(180.dp)
                ) {
                    items(musicList) { music ->
                        MusicCard(
                            music = music,
                            onClick = onClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MusicListHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = modifier.width(7.dp))
        Text(
            text = "音乐",
            color = icon_dark_color,
            style = TextStyle(fontSize = 15.sp)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "to music list details",
            tint = icon_dark_color,
            modifier = modifier.offset(0.dp,(-3).dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more of music list",
                tint = icon_dark_color
            )
        }
    }
}


@Preview
@Composable
fun MusicListPreview() {
    MeditationTheme {
        Surface {
            MusicList(
                musicList = musicList,
                onClick = {}
            )
        }
    }
}

val musicList: List<Music> = listOf(
    Music(R.drawable.shou_tan, "手谈", "专注", "竹林清脆，落子闻音", R.raw.airplane),
    Music(R.drawable.lin_feng, "林风", "冥想", "穿林而过的风", R.raw.boilingwater),
    Music(R.drawable.guang_yun, "光蕴", "情绪", "点点喜悦，束束希望", R.raw.campfire),
    Music(R.drawable.lin_feng, "林风", "专注", "竹林清脆，落子闻音", R.raw.chirpingbirds),
    Music(R.drawable.lin_feng, "手谈", "专注", "竹林清脆，落子闻音", R.raw.nightfall),
    Music(R.drawable.guang_yun, "手谈", "专注", "竹林清脆，落子闻音", R.raw.waterstream),
)