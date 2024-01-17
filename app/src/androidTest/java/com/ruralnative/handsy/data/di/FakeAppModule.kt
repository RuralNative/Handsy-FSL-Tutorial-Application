package com.ruralnative.handsy.data.di

import android.content.Context
import androidx.room.Room
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import com.ruralnative.handsy.data.repository.UserRepository
import com.ruralnative.handsy.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
object FakeAppModule {

    @Provides
    @Singleton
    @Named("test_database")
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
    }

    @Provides
    @Named("test_alphabet_dao")
    @Singleton
    fun provideAlphabetLessonDao(
        database: AppDatabase
    ): AlphabetLessonDao = database.alphabetLessonDao()

    @Provides
    @Named("test_phrases_dao")
    @Singleton
    fun providePhrasesLessonDao(
        database: AppDatabase
    ): PhrasesLessonDao = database.phrasesLessonDao()

    @Provides
    @Named("test_user_dao")
    @Singleton
    fun provideUserDao(
        database: AppDatabase
    ): UserDao = database.userDao()

    @Provides
    @Named("test_alphabet_repo")
    @Singleton
    fun provideAlphabetLessonRepository(): AlphabetLessonRepository = AlphabetLessonRepository()

    @Provides
    @Named("test_phrases_repo")
    @Singleton
    fun providePhrasesLessonRepository(): PhrasesLessonRepository = PhrasesLessonRepository()

    @Provides
    @Named("test_user_repo")
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository()
}