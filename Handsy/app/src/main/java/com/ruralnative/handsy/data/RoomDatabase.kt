package com.ruralnative.handsy.data

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

        private const val NUMBER_OF_THREADS = 4

        val databaseWriteExecutor: ExecutorService = Executor.newFixedThreadPool(NUMBER_OF_THREADS)

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

    abstract fun userDao(): userDao
    abstract fun alphabetLessonsDao: alphabetLessonsDao
    abstract fun phrasesLessonsDao: phrasesLessonsDao

    fun getDatabaseWriteExecutor(): ExecutorService {
        return databaseWriteExecutor
    }
}
