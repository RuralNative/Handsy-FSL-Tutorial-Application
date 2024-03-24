package com.ruralnative.handsy.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User

/**
 * RoomDatabase representing the application database containing the entities and their corresponding DAOs for database interaction
 */
@Database(
    entities = [
        User::class,
        AlphabetLesson::class,
        PhrasesLesson::class
    ],
    version = 4,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun alphabetLessonDao(): AlphabetLessonDao
    abstract fun phrasesLessonDao(): PhrasesLessonDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        /**
         * Returns a singleton instance of the database
         * @param context the application context
         * @return [AppDatabase]
         */
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database.db"
            )
                .createFromAsset("database.db")
                .build()
        }
    }
}