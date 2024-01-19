package com.ruralnative.handsy.di

import android.content.Context
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import com.ruralnative.handsy.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @Database
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
       return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    @AlphabetDAO
    fun provideAlphabetLessonDao(
        database: AppDatabase
    ): AlphabetLessonDao = database.alphabetLessonDao()

    @Provides
    @Singleton
    @PhrasesDAO
    fun providePhrasesLessonDao(
        database: AppDatabase
    ): PhrasesLessonDao = database.phrasesLessonDao()

    @Provides
    @Singleton
    @UserDAO
    fun provideUserDao(
        database: AppDatabase
    ): UserDao = database.userDao()

    @Provides
    @Singleton
    @AlphabetRepo
    fun provideAlphabetLessonRepository(
        @AlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository = AlphabetLessonRepository(dao)

    @Provides
    @Singleton
    @PhrasesRepo
    fun providePhrasesLessonRepository(
        @PhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository = PhrasesLessonRepository(dao)

    @Provides
    @Singleton
    @UserRepo
    fun provideUserRepository(
        @UserDAO dao: UserDao
    ): UserRepository = UserRepository(dao)
}