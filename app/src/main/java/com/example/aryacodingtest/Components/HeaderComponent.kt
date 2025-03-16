package com.example.aryacodingtest.Components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aryacodingtest.Providers.DropDownViewModelPreviewProvider
import com.example.aryacodingtest.R
import com.example.aryacodingtest.ViewModels.DropDownViewModel
import com.example.aryacodingtest.ui.theme.AryaFontFamily


/**
 * Header composable for the app - this makes use of the material TopAppBar component
 *
 * @param dropDownViewModel - view model for the dropdown menu
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderComponent(dropDownViewModel: DropDownViewModel){
    val blur = if (dropDownViewModel.expanded) 13 else 0
    TopAppBar(
        modifier = Modifier
            .blur(blur.dp),
        //navigation icon section
        navigationIcon = {
            IconButton(
                onClick = {
                    Log.d("Back button clicked", "Back button clicked")
                })
            {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_previous),
                    contentDescription = stringResource(R.string.arrow_previous),
                    modifier = Modifier.fillMaxSize(0.4f),
                    tint = Color.White

                )
            }
        },

        //top bar color section
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            navigationIconContentColor = Color.Transparent,
            actionIconContentColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),

        //title section - Profile Image and Profile Name
        title = {
            Row(
                modifier = Modifier.padding(start = 10.dp)
            ){
                //profile image
                Image(
                    painter = painterResource(R.drawable.arya_profileavatars_sarahcarter),
                    contentDescription = stringResource(R.string.profile_image),
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )

                Text("Sarah Carter",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically),
                    fontFamily = AryaFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    maxLines = 1
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewHeaderComponent(@PreviewParameter(DropDownViewModelPreviewProvider::class) dropDownViewModel: DropDownViewModel) {
    HeaderComponent(dropDownViewModel)
}