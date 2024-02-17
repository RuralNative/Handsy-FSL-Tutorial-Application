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

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(
        appDatabase: AppDatabase
    ): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideAlphabetDao(
        appDatabase: AppDatabase
    ): AlphabetLessonDao {
        return appDatabase.alphabetLessonDao()
    }

    @Singleton
    @Provides
    fun providePhrasesDao(
        appDatabase: AppDatabase
    ): PhrasesLessonDao {
        return appDatabase.phrasesLessonDao()
    }
}

