package com.koma.database.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.koma.database.data.entities.People
import com.koma.database.data.entities.Tv

@Database(entities = [Movie::class, People::class, Tv::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
}
