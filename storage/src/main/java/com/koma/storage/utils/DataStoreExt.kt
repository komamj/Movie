package com.koma.storage.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * dataStore.data throws an IOException when an error is encountered when reading data
 */
fun Flow<Preferences>.catchIOException() = this.catch { exception ->
    if (exception is IOException) {
        emit(emptyPreferences())
    } else {
        throw exception
    }
}

fun DataStore<Preferences>.readInt(key: String, defaultValue: Int = -1): Flow<Int> {
    val preferencesKey = preferencesKey<Int>(key)
    return data.catchIOException().map {
            it[preferencesKey] ?: defaultValue
        }
}

suspend fun DataStore<Preferences>.writeInt(key: String, value: Int) {
    val preferencesKey = preferencesKey<Int>(key)
    edit {
        it[preferencesKey] = value
    }
}

fun DataStore<Preferences>.readBoolean(key: String, defaultValue: Boolean = false): Flow<Boolean> {
    val preferencesKey = preferencesKey<Boolean>(key)
    return data.catchIOException().map {
            it[preferencesKey] ?: defaultValue
        }
}

suspend fun DataStore<Preferences>.writeBoolean(key: String, value: Boolean) {
    val preferencesKey = preferencesKey<Boolean>(key)
    edit {
        it[preferencesKey] = value
    }
}

fun DataStore<Preferences>.readString(key: String, defaultValue: String = ""): Flow<String> {
    val preferencesKey = preferencesKey<String>(key)
    return data.catchIOException().map {
        it[preferencesKey] ?: defaultValue
    }
}

suspend fun DataStore<Preferences>.writeString(key: String, value: String) {
    val preferencesKey = preferencesKey<String>(key)
    edit {
        it[preferencesKey] = value
    }
}
