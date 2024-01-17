package com.ruralnative.handsy.data.di

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
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
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
object FakeAppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
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