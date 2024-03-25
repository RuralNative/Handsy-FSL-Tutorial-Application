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
     *
     * @return An instance of UserDao.
     */
    abstract fun userDao(): UserDao

    /**
     * Accessor for the AlphabetLessonDao.
     *
     * @return An instance of AlphabetLessonDao.
     */
    abstract fun alphabetLessonDao(): AlphabetLessonDao

    /**
     * Accessor for the PhrasesLessonDao.
     *
     * @return An instance of PhrasesLessonDao.
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
         * This method ensures that only one instance of the database is created and used throughout the application.
         * It uses the application context to initialize the database.
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
         * Returns a singleton instance of the database for testing purposes.
         *
         * This method is similar to getInstance but is designed for use in testing environments.
         * It creates an in-memory database instance, which is useful for testing without affecting the actual database.
         *
         * @param context The application context.
         * @return The singleton instance of AppDatabase for testing.
         */
        fun getTestInstance(context: Context): AppDatabase {
            return buildInMemoryDatabase(context)
        }

        /**
         * Builds the database instance.
         *
         * This method constructs the database instance using Room's database builder.
         * It specifies the database name and allows the database to be created from an asset file.
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

        /**
         * Builds an in-memory database instance for testing.
         *
         * This method constructs an in-memory database instance, which is useful for testing database operations without persisting data.
         * It allows main thread queries for testing purposes.
         *
         * @param context The application context.
         * @return The built in-memory instance of AppDatabase.
         */
        private fun buildInMemoryDatabase(context: Context): AppDatabase {
            return Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                AppDatabase::class.java
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}