package com.example.onlinenote.data.local

import android.content.Context

class LocalStorage(private val context: Context) {
    private val pref = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE)
}