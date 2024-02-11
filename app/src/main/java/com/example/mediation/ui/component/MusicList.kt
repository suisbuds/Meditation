package com.example.mediation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediation.R
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.icon_dark_color
import com.example.mediation.ui.theme.md_theme_light_brown

@Composable
fun MusicList(
    modifier: Modifier = Modifier,
    musicList: List<Music>,
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .height(250.dp)
            .padding(15.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        LazyColumn(modifier = modifier) {
            item {
                MusicListHeader(modifier = modifier)
                Spacer(modifier = modifier.height(8.dp))
            }
            item {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(125.dp),
                    modifier = Modifier.height(180.dp)
                ) {
                    items(musicList) { music ->
                        MusicCard(music = music)
                    }
                }
            }
        }
    }
}

@Composable
fun MusicListHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "音乐",
            color = md_theme_light_brown,
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "to music list details",
            tint = icon_dark_color
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

val musicList: List<Music> = listOf(
    Music(R.drawable.shou_tan, "手谈", "专注", "竹林清脆，落子闻音"),
    Music(R.drawable.lin_feng, "林风", "冥想", "穿林而过的风"),
    Music(R.drawable.guang_yun, "光蕴", "情绪", "点点喜悦，束束希望"),
    Music(R.drawable.shou_tan, "", "", ""),
    Music(R.drawable.lin_feng, "", "", ""),
    Music(R.drawable.guang_yun, "", "", ""),
)

@Preview
@Composable
fun MusicListPreview() {
    MediationTheme {
        Surface {
            MusicList(
                musicList = musicList
            )
        }
    }
}