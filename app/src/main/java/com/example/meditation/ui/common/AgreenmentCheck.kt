package com.example.meditation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown
import com.example.meditation.ui.theme.login_screen_color

//用户协议
@Composable
fun AgreementCheck(
    modifier: Modifier = Modifier,
    checked:Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(15.dp)
                .background(icon_color_brown)
                .padding(1.dp)
                .clip(CircleShape)
                .background(login_screen_color)
                .toggleable(
                    value = checked,
                    role = Role.Checkbox,
                    onValueChange = { onClick() }),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = icon_color_brown
                )
            }
        }
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = "点击阅读并同意《用户使用协议》和隐私协议",
            fontSize = 12.sp,
            color = icon_dark_color_brown,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Medium,
            modifier = modifier
        )
    }
}

