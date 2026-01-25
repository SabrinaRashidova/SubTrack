package com.sabrina.subtrack.ui

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sabrina.subtrack.ui.screens.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "settings")

class ThemeSession( private val context: Context) {
    private val THEME_KEY = stringPreferencesKey("app_theme")

    val themeFlow: Flow<AppTheme> = context.dataStore.data
        .map { preferences ->
            val themeName = preferences[THEME_KEY] ?: AppTheme.LIGHT.name
            AppTheme.valueOf(themeName)
        }

    suspend fun saveTheme(theme: AppTheme){
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }
}