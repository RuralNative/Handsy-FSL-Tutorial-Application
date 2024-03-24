package com.ruralnative.handsy.di

import android.content.Context
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton

/**
 * Hilt module for providing database dependencies.
 *
 * This module is responsible for providing instances of the database and its DAOs.
 * It uses Hilt's dependency injection mechanism to ensure that these instances are
 * available throughout the application where needed.
 *
 * @constructor Creates a new instance of DatabaseModule.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    /**
     * Provides an instance of the local database.
     *
     * This method is annotated with `@Singleton` to ensure that only one instance of the
     * database is created and used throughout the application. It uses the application context
     * to initialize the database.
     *
     * @param context The application context.
     * @return An instance of [AppDatabase].
     */
    @Singleton
    @Provides
    @Named("test_db")
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    /**
     * Provides an instance of the [UserDao].
     *
     * This method retrieves the [UserDao] from the [AppDatabase] instance.
     *
     * @param appDatabase The instance of [AppDatabase].
     * @return An instance of [UserDao].
     */
    @Provides
    fun provideUserDao(
        appDatabase: AppDatabase
    ): UserDao {
        return appDatabase.userDao()
    }

    /**
     * Provides an instance of the [AlphabetLessonDao].
     *
     * This method retrieves the [AlphabetLessonDao] from the [AppDatabase] instance.
     *
     * @param appDatabase The instance of [AppDatabase].
     * @return An instance of [AlphabetLessonDao].
     */
    @Provides
    fun provideAlphabetDao(
        appDatabase: AppDatabase
    ): AlphabetLessonDao {
        return appDatabase.alphabetLessonDao()
    }

    /**
     * Provides an instance of the [PhrasesLessonDao].
     *
     * This method retrieves the [PhrasesLessonDao] from the [AppDatabase] instance.
     *
     * @param appDatabase The instance of [AppDatabase].
     * @return An instance of [PhrasesLessonDao].
     */
    @Singleton
    @Provides
    fun providePhrasesDao(
        appDatabase: AppDatabase
    ): PhrasesLessonDao {
        return appDatabase.phrasesLessonDao()
    }
}