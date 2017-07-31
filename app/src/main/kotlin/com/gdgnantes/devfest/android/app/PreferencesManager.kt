package com.gdgnantes.devfest.android.app

import android.content.Context
import android.content.SharedPreferences
import com.gdgnantes.devfest.android.AppConfig
import com.gdgnantes.devfest.android.content.SharedPreferencesOpenHelper
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferencesManager private constructor(context: Context) {

    private val openHelper = PreferencesOpenHelper(context)

    companion object {

        private var instance: PreferencesManager? = null

        fun from(context: Context): PreferencesManager {
            synchronized(PreferencesManager::class) {
                if (instance == null) {
                    instance = PreferencesManager(context.applicationContext)
                }
                return instance!!
            }
        }

        private interface Keys {
            companion object {
                const val SELECTED_TAB = "prefs.selectedTab"
                const val BOOKMARKS = "prefs.bookmarks"
                const val SCHEDULE_ETAG = "prefs.scheduleETag"
            }
        }
    }

    var selectedTab: String? by openHelper.sharedPreferences.string(Keys.SELECTED_TAB)
    var bookmarks: Set<String> by openHelper.sharedPreferences.stringSet(Keys.BOOKMARKS)
    var scheduleETag: String? by openHelper.sharedPreferences.string(Keys.SCHEDULE_ETAG)

    private class PreferencesOpenHelper(context: Context) : SharedPreferencesOpenHelper(context, NAME, VERSION) {

        companion object {
            private val NAME = "app_prefs"
            private val VERSION = 1
        }

        override fun onUpgrade(editor: SharedPreferences.Editor, oldVersion: Int, newVersion: Int) {
//            var version = oldVersion
//            loop@ while (version != newVersion) {
//                when (version) {
//                    1 -> {
//                        version = upgradeToVersion2()
//                    }
//                    else -> {
//                        editor.clear()
//                        break@loop
//                    }
//                }
//            }
        }

//        private fun upgradeToVersion2(): Int {
//            return 2
//        }

    }

}

private fun SharedPreferences.string(name: String, defaultValue: String? = null)
        = object : ReadWriteProperty<Any, String?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        edit().putString(name, value).apply()
    }
}

private fun SharedPreferences.stringSet(name: String, defaultValue: Set<String> = emptySet())
        = object : ReadWriteProperty<Any, Set<String>> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Set<String> {
        return getStringSet(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Set<String>) {
        edit().putStringSet(name, value).apply()
    }
}

