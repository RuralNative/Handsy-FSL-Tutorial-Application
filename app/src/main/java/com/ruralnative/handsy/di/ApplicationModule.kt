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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    @Database
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
       return AppDatabase.getDatabase(context)
    }

    @Provides
    @AlphabetDAO
    fun provideAlphabetLessonDao(
        @Database database: AppDatabase
    ): AlphabetLessonDao = database.alphabetLessonDao()

    @Provides
    @PhrasesDAO
    fun providePhrasesLessonDao(
        @Database database: AppDatabase
    ): PhrasesLessonDao = database.phrasesLessonDao()

    @Provides
    @UserDAO
    fun provideUserDao(
        @Database database: AppDatabase
    ): UserDao = database.userDao()
}