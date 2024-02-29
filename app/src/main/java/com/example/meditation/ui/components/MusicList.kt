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
import com.example.meditation.R
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.icon_dark_color_brown

@Composable
fun MusicList(
    modifier: Modifier = Modifier,
    musicList: List<Music>,
    currentMusicIndex: Int,
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
                        val index = musicList.indexOf(music)
                        MusicCard(
                            music = music,
                            selected = index == currentMusicIndex,
                            onClick = { onClick(music) }
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
            color = icon_dark_color_brown,
            style = TextStyle(fontSize = 15.sp)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "to music list details",
            tint = icon_dark_color_brown,
            modifier = modifier.offset(0.dp, (-3).dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more of music list",
                tint = icon_dark_color_brown
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
                currentMusicIndex = 1,
                onClick = {}
            )
        }
    }
}

val musicList: List<Music> = listOf(
    Music(R.drawable.shou_tan, "竹林", "专注", "竹林清脆，落子闻音", R.raw.zhu_lin),
    Music(R.drawable.lin_feng, "故梦", "情绪", "人间何所以，观风与月舒", R.raw.gu_meng),
    Music(R.drawable.guang_yun, "潺潺", "冥想", "静坐溪畔，踏青风明月", R.raw.chan_chan),
    Music(R.drawable.bai_hua_lin, "相许", "情绪", "祝眉目舒展，顺问冬安", R.raw.xiang_xu),
    Music(R.drawable.the_swell_season, "江湖", "冥想", "围炉煮茶，叹长歌", R.raw.jiang_hu),
    Music(R.drawable.mountain, "慢慢", "冥想", "阳光洒下一地斑驳，想着从前", R.raw.man_man),
)