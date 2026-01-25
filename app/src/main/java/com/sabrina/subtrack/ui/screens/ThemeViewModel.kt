package com.sabrina.subtrack.ui.screens

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.subtrack.ui.ThemeSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeSession: ThemeSession
): ViewModel() {
    val currentTheme = themeSession.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppTheme.LIGHT
    )
    fun setTheme(theme: AppTheme){
        viewModelScope.launch {
            themeSession.saveTheme(theme)
        }
    }
}