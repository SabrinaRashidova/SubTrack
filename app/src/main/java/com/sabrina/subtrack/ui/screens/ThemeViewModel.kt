package com.sabrina.subtrack.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemeViewModel: ViewModel() {
    var currentTheme by mutableStateOf(AppTheme.LIGHT)
        private set

    fun setTheme(theme: AppTheme) {
        currentTheme = theme
    }
}