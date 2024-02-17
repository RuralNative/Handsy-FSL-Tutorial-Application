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

@Database(
    entities = [
        User::class,
        AlphabetLesson::class,
        PhrasesLesson::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun alphabetLessonDao(): AlphabetLessonDao
    abstract fun phrasesLessonDao(): PhrasesLessonDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create a new table with the correct schema
                db.execSQL("""
            CREATE TABLE user_new (
                id INTEGER PRIMARY KEY,
                progression_level INTEGER NOT NULL,
                user_name TEXT,
                is_new_user INTEGER NOT NULL
            )
        """)

                // Copy the data from the old table to the new one
                db.execSQL("""
            INSERT INTO user_new (id, progression_level, user_name, is_new_user)
            SELECT id, progression_level, user_name, is_new_user FROM user
        """)

                // Remove the old table
                db.execSQL("DROP TABLE user")

                // Rename the new table to the correct name
                db.execSQL("ALTER TABLE user_new RENAME TO user")
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database.db"
            )
                .addMigrations(MIGRATION_1_2)
                .createFromAsset("database.db")
                .build()
        }
    }
}