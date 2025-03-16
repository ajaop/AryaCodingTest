package com.example.aryacodingtest.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.aryacodingtest.Providers.DropDownViewModelPreviewProvider
import com.example.aryacodingtest.R
import com.example.aryacodingtest.ViewModels.DropDownViewModel
import com.example.aryacodingtest.ui.theme.AryaFontFamily
import com.example.aryacodingtest.ui.theme.alloy1
import com.example.aryacodingtest.ui.theme.alloy2
import com.example.aryacodingtest.ui.theme.greenBeach1
import com.example.aryacodingtest.ui.theme.greenBeach2
import com.example.aryacodingtest.ui.theme.plum1
import com.example.aryacodingtest.ui.theme.plum2
import com.example.aryacodingtest.ui.theme.sunshine1
import com.example.aryacodingtest.ui.theme.sunshine2

/**
 * Dropdown menu composable for the app - this makes use of the material DropdownMenu component
 *
 * @param dropDownViewModel - view model for the dropdown menu
 */
@Composable
fun DropDownMenuComponent(dropDownViewModel: DropDownViewModel) {

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color.Transparent,
            onBackground = Color.Transparent,
            surface = Color.Transparent
        )
    ){

        DropdownMenu(
            modifier = Modifier
                .fillMaxSize()
                .clickable { dropDownViewModel.toggleDropdown() }
                .wrapContentSize(Alignment.BottomStart)
                .padding(bottom = 50.dp),
            offset = DpOffset(0.dp,0.dp),
            expanded = dropDownViewModel.expanded,
            properties = PopupProperties(clippingEnabled = false),
            onDismissRequest = { dropDownViewModel.toggleDropdown()},
        ) {

            //Camera Dropdown Item,
            DropDownItemDecorated("Camera", listOf(alloy1, alloy2), R.drawable.icon_camera, R.string.icon_camera)

            //Photos Dropdown Item,
            DropDownItemDecorated("Photos", listOf(sunshine1, sunshine2), R.drawable.icon_photos, R.string.icon_photo)

            //Files Dropdown Item,
            DropDownItemDecorated("Files", listOf(greenBeach1, greenBeach2), R.drawable.icon_files, R.string.icon_file)

            //Audio Dropdown Item,
            DropDownItemDecorated("Audio", listOf(plum1, plum2), R.drawable.icon_audio, R.string.icon_audio)

        }

    }

}

/**
 * Dropdown item composable for the app - this makes use of the material DropdownMenuItem component
 *
 * @param text - text to be displayed in the dropdown item
 * @param colors - colors to be used for the dropdown item
 * @param iconResource - resource for the icon to be displayed in the dropdown item
 * @param iconDescriptionResource - description for the icon to be displayed in the dropdown item
 *
 */
@Composable
fun DropDownItemDecorated(text: String, colors:  List<Color>, iconResource : Int, iconDescriptionResource: Int){
    DropdownMenuItem(
        modifier = Modifier.padding(10.dp),
        text = { Text(text,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color.White,
                fontFamily = AryaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        ) },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = colors, // Gradient colors
                            start = Offset(0f, 0f),
                            end = Offset(100f, 200f) // Direction of the gradient
                        ),
                        shape = CircleShape
                    )
                    .padding(15.dp)
            ) {
                Icon(
                    modifier = Modifier.size(19.dp),
                    painter = painterResource(iconResource),
                    contentDescription = stringResource(iconDescriptionResource),
                    tint = Color.White
                )
            }
        },
        onClick = { Log.d("Drop Down Item", "Clicked")}
    )
}

@Preview(showBackground = true)
@Composable
fun DropDownComponentPreview(@PreviewParameter(DropDownViewModelPreviewProvider::class) dropDownViewModel: DropDownViewModel) {
    DropDownMenuComponent(dropDownViewModel)
}