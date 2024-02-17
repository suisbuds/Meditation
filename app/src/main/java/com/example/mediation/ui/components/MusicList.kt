package com.example.mediation.ui.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.icon_dark_color
import com.example.mediation.ui.theme.md_theme_light_brown
import com.example.mediation.ui.viewmodel.musicList

@Composable
fun MusicList(
    modifier: Modifier = Modifier,
    musicList: List<Music>,
    onClick: (Music) -> Unit
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .height(260.dp)
            .padding(15.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        LazyColumn(modifier = modifier.padding(8.dp)) {
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
        Text(
            text = "音乐",
            color = icon_dark_color,
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


@Preview
@Composable
fun MusicListPreview() {
    MediationTheme {
        Surface {
            MusicList(
                musicList = musicList,
                onClick = {}
            )
        }
    }
}