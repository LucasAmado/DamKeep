package com.lucasamado.damkeepapp.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    fun getSharedPreferences(): SharedPreferences {
        return MyApp.instance?.getSharedPreferences("SHARED_PREFERENCES_FILE", Context.MODE_PRIVATE)
    }

    fun setStringValue(label: String, value: String) {
        var editor: SharedPreferences.Editor = getSharedPreferences().edit()
        editor.putString(label, value)
        editor.commit()
    }


    fun getSomeStringValue(label: String?): String? {
        return getSharedPreferences().getString(label, null)
    }

}