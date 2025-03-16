package com.example.aryacodingtest.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DropDownViewModel : ViewModel() {
    var expanded by mutableStateOf(false) // Global variable
        private set

    fun toggleDropdown() {
        expanded = !expanded
    }
}