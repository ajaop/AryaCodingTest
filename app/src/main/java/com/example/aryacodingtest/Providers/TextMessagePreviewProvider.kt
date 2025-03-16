package com.example.aryacodingtest.Providers
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

// PreviewParameterProvider to provide mock values for the TextMessageComponent
class TextMessagePreviewProvider : PreviewParameterProvider<Triple<String, String, Boolean>> {
    override val values = sequenceOf(
        Triple("Hello, how are you?", "10:30 AM", true),  // Received message
        Triple("I'm doing great, thanks!", "10:31 AM", false)  // Sent message
    )
}
