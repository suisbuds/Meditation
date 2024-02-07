package com.example.mediation.ui.component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediation.R
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.md_theme_light_brown
import com.example.mediation.ui.theme.md_theme_light_gold
import com.example.mediation.ui.theme.md_theme_orange

@Composable
fun MusicCard(
    modifier: Modifier = Modifier,
    music: Music,
) {
    Row(modifier = modifier) {
        Spacer(modifier = modifier.width(25.dp))
        Image(
            painter = painterResource(id = music.imageId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = modifier.width(15.dp))
        Description(
            modifier = modifier,
            title = music.title,
            subtitle = music.subtitle,
            tag = music.tag
        )
    }
}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    tag: String
) {
    Column {
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = modifier.height(10.dp))
        Row(modifier = modifier) {
            Surface(
                color = md_theme_light_gold,
                border = BorderStroke(1.5.dp, md_theme_orange),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = tag,
                    modifier = Modifier.padding(4.dp),
                    color = md_theme_light_brown,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = modifier.width(8.dp))
            Text(text = subtitle, color = Color.Gray)
        }
    }
}

@Preview
@Composable
fun MusicCardPreview() {
    MediationTheme {
        Surface {
            MusicCard(
                music = Music(
                    R.drawable.shou_tan,
                    "手谈",
                    "专注",
                    "竹林清脆，落子闻音"
                )
            )
        }
    }
}

data class Music(
    val imageId: Int,
    val title: String,
    val tag: String,
    val subtitle: String
)




