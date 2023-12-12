package com.ruralnative.handsy.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    version = 1,
    entities = [
        user::class,
        alphabet_lessons::class,
        phrases_lessons::class
    ]
)
abstract class Database : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(application: Application): Database {
            if (INSTANCE == null) {
                synchronized(Database::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            application.applicationContext,
                            Database::class, "application_database.db"
                        )
                            .createFromAsset("database.db")
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    fun getCoroutine(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

    abstract fun userDao(): userDao
    abstract fun alphabetLessonsDao(): alphabetLessonsDao
    abstract fun phrasesLessonsDao(): phrasesLessonsDao

    fun getDatabaseWriteExecutor(): ExecutorService {
        return databaseWriteExecutor
    }
}
