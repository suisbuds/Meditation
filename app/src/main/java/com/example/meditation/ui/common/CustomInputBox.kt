package com.example.meditation.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.login_icon_color

@Composable
fun CustomInputBox(
    modifier: Modifier = Modifier,
    onValueChange: () -> Unit = {},
    imageId: Int,
    keyboardType: KeyboardType,
    textTint: String
) {
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        leadingIcon = { Icon(painter = painterResource(id = imageId), contentDescription = "") },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
        onValueChange = { onValueChange },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = {
            AnimatedVisibility(visible = text == "") {
                Text(
                    text = textTint,
                    color = login_icon_color,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp,
                )
            }
        },
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.border(width = 0.dp, color = Color.Transparent),
        textStyle = TextStyle(
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = login_icon_color
        ),
    )
}