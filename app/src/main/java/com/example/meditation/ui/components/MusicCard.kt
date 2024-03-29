package com.example.meditation.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.R
import com.example.meditation.ui.theme.*

@Composable
fun MusicCard(
    modifier: Modifier = Modifier,
    music: Music,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .width(250.dp)
            .padding(horizontal = 3.dp),
        shape = RoundedCornerShape(5.dp),
        color = Color.Transparent
    ) {
        Row(modifier = modifier.selectable(selected, onClick = onClick, role = Role.RadioButton)) {
            Spacer(modifier = modifier.width(5.dp))
            Image(
                painter = painterResource(id = music.imageId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = modifier.width(5.dp))
            Description(
                modifier = modifier,
                title = music.title,
                subtitle = music.subtitle,
                tag = music.tag
            )
            Column(modifier = modifier.fillMaxWidth()) {
                Spacer(modifier = modifier.height(10.dp))
                Box(modifier = modifier.align(Alignment.End)) {
                    RadioButton(
                        selected = selected, onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = icon_dark_color_brown,
                            unselectedColor = icon_dark_color_brown
                        )
                    )
                }
            }


        }
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
        Spacer(modifier = modifier.height(1.dp))
        Text(
            text = title,
            style = TextStyle(fontSize = 15.sp),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.height(4.dp))
        Row(modifier = modifier) {
            Surface(
                color = md_theme_light_gold,
                border = BorderStroke(1.5.dp, md_theme_orange),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = tag,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.5.dp),
                    color = md_theme_light_brown,
                    style = TextStyle(fontSize = 6.sp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = modifier.width(5.dp))
            Text(
                text = subtitle,
                color = md_theme_gray,
                style = TextStyle(fontSize = 10.sp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun MusicCardPreview() {
    MeditationTheme {
        Surface {
            MusicCard(
                music = Music(
                    R.drawable.shou_tan,
                    "手谈",
                    "专注",
                    "竹林清脆，落子闻音",
                    R.raw.gu_meng
                ),
                selected = true,
                onClick = {}
            )
        }
    }
}

data class Music(
    val imageId: Int,
    val title: String,
    val tag: String,
    val subtitle: String,
    val musicId: Int
)




