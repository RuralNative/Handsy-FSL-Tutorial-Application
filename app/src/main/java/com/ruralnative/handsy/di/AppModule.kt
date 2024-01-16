package com.ruralnative.handsy.di

import android.content.Context
import android.widget.AlphabetIndexer
import androidx.room.Room
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
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
       return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAlphabetLessonDao(
        database: AppDatabase
    ): AlphabetLessonDao = database.alphabetLessonDao()

    @Provides
    @Singleton
    fun providePhrasesLessonDao(
        database: AppDatabase
    ): PhrasesLessonDao = database.phrasesLessonDao()

    @Provides
    @Singleton
    fun provideUserDao(
        database: AppDatabase
    ): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideAlphabetLessonRepository(): AlphabetLessonRepository = AlphabetLessonRepository()

    @Provides
    @Singleton
    fun providePhrasesLessonRepository(): PhrasesLessonRepository = PhrasesLessonRepository()

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository()
}