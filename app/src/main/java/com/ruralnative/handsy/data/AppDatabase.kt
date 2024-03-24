package com.ruralnative.handsy.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User

/**
 * RoomDatabase representing the application database containing the entities and their corresponding DAOs for database interaction.
 *
 * This class defines the database schema and provides access to the DAOs for performing CRUD operations on the database.
 * It includes entities for User, AlphabetLesson, and PhrasesLesson.
 *
 * @property userDao DAO for accessing User data.
 * @property alphabetLessonDao DAO for accessing AlphabetLesson data.
 * @property phrasesLessonDao DAO for accessing PhrasesLesson data.
 *
 * @constructor Creates a new instance of AppDatabase.
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

    /**
     * Accessor for the UserDao.
     */
    abstract fun userDao(): UserDao

    /**
     * Accessor for the AlphabetLessonDao.
     */
    abstract fun alphabetLessonDao(): AlphabetLessonDao

    /**
     * Accessor for the PhrasesLessonDao.
     */
    abstract fun phrasesLessonDao(): PhrasesLessonDao

    companion object {

        /**
         * Singleton instance of the database.
         */
        @Volatile private var instance: AppDatabase? = null

        /**
         * Returns a singleton instance of the database.
         *
         * @param context The application context.
         * @return The singleton instance of AppDatabase.
         */
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        /**
         * Builds the database instance.
         *
         * @param context The application context.
         * @return The built instance of AppDatabase.
         */
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