package com.example.news.library

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

class Theme(private val context:Context) {
    fun fixedThemeLight(){

        when (context.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            Configuration.UI_MODE_NIGHT_NO->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }
}