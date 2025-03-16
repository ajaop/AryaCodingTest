package com.example.aryacodingtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aryacodingtest.Components.BottomBarComponent
import com.example.aryacodingtest.Components.DropDownMenuComponent
import com.example.aryacodingtest.Components.HeaderComponent
import com.example.aryacodingtest.Components.TextMessageComponent
import com.example.aryacodingtest.ViewModels.DropDownViewModel
import com.example.aryacodingtest.ui.theme.AryaCodingTestTheme
import com.example.aryacodingtest.ui.theme.aquaBlue
import com.example.aryacodingtest.ui.theme.beige
import com.example.aryacodingtest.ui.theme.skyBlue
import com.example.aryacodingtest.ui.theme.teal

class MainActivity : ComponentActivity() {

    private val dropDownViewModel: DropDownViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AryaCodingTestTheme {
                AryaApp()
            }
        }
    }


/**
 * composable for adding the arya gradient theme to the app
 *
 * @param content - content to be displayed in the background
 */
@Composable
fun Background(content: @Composable () -> Unit){

    //Arya gradient theme
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            skyBlue,
            aquaBlue,
            teal,
            beige
        ),
        start = Offset(0f, 0f),
        end = Offset(1550f, 1000f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        content()
    }
}


/**
 * composable for most of the app components
 *
 */
@Composable
fun AryaApp(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HeaderComponent(dropDownViewModel) },
        bottomBar = { BottomBarComponent(dropDownViewModel) }) { innerPadding ->
        Background {

            val blurValue by animateDpAsState(
                targetValue = if (dropDownViewModel.expanded) 16.dp else 0.dp
            )

            //BODY
            Column(
                modifier = Modifier
                    .blur(blurValue)
                    .padding(innerPadding).padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                TextMessageComponent("Hey John, let's get together and discuss the job proposal. Does Monday work?", "11:48 AM", true)

                // Spacer to add space between items
                Spacer(modifier = Modifier.height(15.dp))

                TextMessageComponent("That would be great. Yes, I will see you on Monday.", "1:54 PM", false)
            }

            if (dropDownViewModel.expanded) {


                // Animate background opacity
                val backgroundAlpha by animateFloatAsState(
                    targetValue = if (dropDownViewModel.expanded) 0.3f else 0f,
                    animationSpec = tween(durationMillis = 600) // Slower fade-in/out
                )
                AnimatedVisibility(
                    visibleState = MutableTransitionState(dropDownViewModel.expanded),
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it }) + expandVertically(),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { it }) + shrinkVertically()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(blurValue)
                            .background(Color.Black.copy(alpha = backgroundAlpha))
                    )
                }

            }

            DropDownMenuComponent(dropDownViewModel)

        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AryaAppPreview(dropDownViewModel : DropDownViewModel = DropDownViewModel()) {

    AryaCodingTestTheme {
        AryaApp()
    }
}
    }
