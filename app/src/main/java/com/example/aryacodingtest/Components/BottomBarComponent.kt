package com.example.aryacodingtest.Components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aryacodingtest.Providers.DropDownViewModelPreviewProvider
import com.example.aryacodingtest.R
import com.example.aryacodingtest.ViewModels.DropDownViewModel
import com.example.aryacodingtest.ui.theme.AryaFontFamily
import com.example.aryacodingtest.ui.theme.brown


/**
 * Bottom bar composable for the app - this makes use of the material BottomAppBar component
 *
 * @param dropDownViewModel - view model for the dropdown menu
 */
@Composable
fun BottomBarComponent(dropDownViewModel: DropDownViewModel) {
    var text by remember { mutableStateOf("") }
    val blur = if (dropDownViewModel.expanded) 13 else 0

    BottomAppBar(

        modifier = Modifier
            .blur(blur.dp),
        actions = {
            IconButton(
                onClick = {
                    dropDownViewModel.toggleDropdown()
                })
            {
                Icon(
                    painter = painterResource(R.drawable.icon_plus),
                    contentDescription = stringResource(R.string.icon_plus),
                    modifier = Modifier.fillMaxSize(0.5f),
                    tint = Color.White
                )
            }

            TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                trailingIcon =  {
                    AnimatedVisibility(
                        visible = text.isNotBlank(),
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut()
                    ) {
                        IconButton(
                            modifier = Modifier.size(33.dp),
                            colors = IconButtonColors(
                                containerColor = Color.White,
                                contentColor = brown,
                                disabledContainerColor = Color.White,
                                disabledContentColor = brown
                            ),
                            onClick = {
                                Log.d("Send button clicked", "Send button clicked")
                            }){
                            Icon(
                                painter = painterResource(R.drawable.icon_sendmessage),
                                contentDescription = stringResource(R.string.icon_send_message),
                                modifier = Modifier.fillMaxSize(0.5f),
                                tint = brown
                            )
                        }
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontFamily = AryaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                placeholder = { Text(
                    "Message",
                    color = Color.White,
                    fontFamily = AryaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.Black.copy(alpha = 0.05f))
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(100.dp),
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.35f), // Top
                                Color.Black.copy(alpha = 0.35f), // Left
                                Color.White.copy(alpha = 0.35f), // Right
                                Color.White.copy(alpha = 0.35f), // Bottom
                            )
                        )
                    )
                    .padding(0.dp), // Ensures text doesn't overlap border
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                )
            )
        },
        containerColor = Color.Transparent,
    )
}

@Preview(showBackground = true)
@Composable
fun BottomBar(@PreviewParameter(DropDownViewModelPreviewProvider::class) dropDownViewModel: DropDownViewModel) {
    BottomBarComponent(dropDownViewModel)
}