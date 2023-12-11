package com.ruralnative.handsy.data

@Database(
    version = 1,
    entities = [
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
                            Database::class, "INSERT_DATABASE_NAME"
                        )
                            .createFromAsset("DATABASE_FILE")
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    fun getDatabaseWriteExecutor(): ExecutorService {
        return databaseWriteExecutor
    }
}
