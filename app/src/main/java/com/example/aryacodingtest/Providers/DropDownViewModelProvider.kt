package com.example.aryacodingtest.Providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.aryacodingtest.ViewModels.DropDownViewModel

// Custom PreviewParameterProvider to provide com.example.aryacodingtest.ViewModels.DropDownViewModel for preview
class DropDownViewModelPreviewProvider : PreviewParameterProvider<DropDownViewModel> {
    override val values = sequenceOf(DropDownViewModel()) // Provide an instance of com.example.aryacodingtest.ViewModels.DropDownViewModel
}
