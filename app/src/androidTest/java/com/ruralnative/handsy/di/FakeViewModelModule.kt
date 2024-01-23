package com.ruralnative.handsy.di

import android.content.Context
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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [ViewModelComponent::class], replaces = [ViewModelModule::class])
object FakeViewModelModule {
    @Provides
    @TestAlphabetDAO
    fun provideAlphabetLessonDao(
        @TestDatabase database: AppDatabase
    ): AlphabetLessonDao = database.alphabetLessonDao()

    @Provides
    @TestPhrasesDAO
    fun providePhrasesLessonDao(
        @TestDatabase database: AppDatabase
    ): PhrasesLessonDao = database.phrasesLessonDao()

    @Provides
    @TestUserDAO
    fun provideUserDao(
        @TestDatabase database: AppDatabase
    ): UserDao = database.userDao()

    @Provides
    @Singleton
    @TestAlphabetRepo
    fun provideAlphabetLessonRepository(
        @TestAlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository = AlphabetLessonRepository(dao)

    @Provides
    @Singleton
    @TestPhrasesRepo
    fun providePhrasesLessonRepository(
        @TestPhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository = PhrasesLessonRepository(dao)

    @Provides
    @Singleton
    @TestUserRepo
    fun provideUserRepository(
        @TestUserDAO dao: UserDao
    ): UserRepository = UserRepository(dao)
}