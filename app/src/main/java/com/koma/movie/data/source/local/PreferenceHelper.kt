/*
 * Copyright 2020 komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.koma.movie.data.source.local

import android.app.Application
import android.content.Context
import androidx.annotation.Nullable
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceHelper @Inject constructor(application: Application) {
    private val sharedPreferences = application.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    private fun getString(key: String, @Nullable defValue: String): String {
        return sharedPreferences.getString(key, defValue) ?: return ""
    }

    private fun setString(key: String, @Nullable value: String, commit: Boolean = false) {
        sharedPreferences.edit(commit = commit) {
            putString(key, value)
        }
    }

    private fun getBoolean(key: String, @Nullable defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    private fun setBoolean(key: String, @Nullable value: Boolean, commit: Boolean = false) {
        sharedPreferences.edit(commit = commit) {
            putBoolean(key, value)
        }
    }

    companion object {
        private const val NAME = "pref_movie"
    }
}
