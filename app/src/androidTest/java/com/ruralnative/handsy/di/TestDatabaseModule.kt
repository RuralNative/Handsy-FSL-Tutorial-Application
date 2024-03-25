package com.ruralnative.handsy.di

import android.content.Context
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton

/**
 * Hilt module for providing test database dependencies.
 *
 * This module is designed to provide instances of the test database and its DAOs for testing purposes.
 * It uses Hilt's dependency injection mechanism to ensure that these instances are available throughout the application where needed, specifically for testing.
 * It replaces the production database module to inject test instances of the database and DAOs.
 *
 * @constructor Creates a new instance of TestDatabaseModule.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    /**
     * Provides an instance of the test database.
     *
     * This method is annotated with `@Singleton` and `@Named("test_db")` to ensure that only one instance of the
     * test database is created and used throughout the application for testing. It uses the application context
     * to initialize the test database.
     *
     * @param context The application context.
     * @return An instance of [AppDatabase] for testing.
     */
    @Singleton
    @Provides
    @Named("test_db")
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getTestInstance(context)
    }

    /**
     * Provides an instance of [UserDao] for testing.
     *
     * This method is used to inject a test instance of [UserDao] into the application.
     *
     * @param appDatabase The test instance of [AppDatabase].
     * @return An instance of [UserDao].
     */
    @Provides
    fun provideUserDao(
        @Named("test_db") appDatabase: AppDatabase
    ): UserDao {
        return appDatabase.userDao()
    }

    /**
     * Provides an instance of [AlphabetLessonDao] for testing.
     *
     * This method is used to inject a test instance of [AlphabetLessonDao] into the application.
     *
     * @param appDatabase The test instance of [AppDatabase].
     * @return An instance of [AlphabetLessonDao].
     */
    @Provides
    fun provideAlphabetDao(
        @Named("test_db") appDatabase: AppDatabase
    ): AlphabetLessonDao {
        return appDatabase.alphabetLessonDao()
    }

    /**
     * Provides an instance of [PhrasesLessonDao] for testing.
     *
     * This method is used to inject a test instance of [PhrasesLessonDao] into the application.
     *
     * @param appDatabase The test instance of [AppDatabase].
     * @return An instance of [PhrasesLessonDao].
     */
    @Provides
    fun providePhrasesDao(
        @Named("test_db") appDatabase: AppDatabase
    ): PhrasesLessonDao {
        return appDatabase.phrasesLessonDao()
    }
}

