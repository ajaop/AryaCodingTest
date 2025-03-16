package com.example.aryacodingtest.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aryacodingtest.Providers.TextMessagePreviewProvider
import com.example.aryacodingtest.R
import com.example.aryacodingtest.ui.theme.AryaFontFamily
import com.example.aryacodingtest.ui.theme.primaryBlue


/**
 * text message composable - this shows the text received or sent  by the user with required formatting
 *
 * @param message - text to be displayed
 * @param time - time of the text to be displayed
 * @param isReceived - check if its a recieved text or sent text
 */
@Composable
fun TextMessageComponent(message: String, time: String, isReceived: Boolean){

    //if is received apply brush else don't apply brush
    val brush = if (isReceived) {
        Brush.verticalGradient(
            colors = listOf(
                Color.Black.copy(alpha = 0.35f), // Top
                Color.Black.copy(alpha = 0.35f), // Left
                Color.White.copy(alpha = 0.35f), // Right
                Color.White.copy(alpha = 0.35f), // Bottom
            )
        )
    } else {
        null // If false, don't apply any brush
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (!isReceived) 60.dp else 0.dp,
                end = if (isReceived) 60.dp else 0.dp
            )
            .clip(RoundedCornerShape(14.dp))
            .background(if (!isReceived) Color.White else Color.Black.copy(alpha = 0.05f))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(14.dp),
                brush = brush ?: SolidColor(Color.Transparent)
            )

    ) {
        Column(modifier = Modifier.padding(top =  8.dp, bottom =  8.dp, start =  12.dp, end =  12.dp, ),) {

            Text(
                text = message,
                style = TextStyle(
                    color = if (!isReceived) Color.Black else Color.White,
                    fontFamily = AryaFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End)
            {
                Text(
                    text = time,
                    style = TextStyle(
                        color = if (!isReceived) primaryBlue else Color.White,
                        fontFamily = AryaFontFamily,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End,
                        fontSize = 12.sp
                    )
                )

                if (!isReceived) {
                    Spacer(modifier = Modifier.width(5.dp))

                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.icon_chat_read),
                        contentDescription = stringResource(R.string.icon_chat_read),
                        tint = primaryBlue
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun TextMessageComponentPreview(@PreviewParameter(TextMessagePreviewProvider::class) messageData: Triple<String, String, Boolean>) {
    val (message, time, isReceived) = messageData
    TextMessageComponent(message = message, time = time, isReceived = isReceived)
}