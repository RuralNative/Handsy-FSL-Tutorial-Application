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
import com.ruralnative.handsy.di.AlphabetDAO
import com.ruralnative.handsy.di.AlphabetRepo
import com.ruralnative.handsy.di.AppModule
import com.ruralnative.handsy.di.PhrasesDAO
import com.ruralnative.handsy.di.PhrasesRepo
import com.ruralnative.handsy.di.UserDAO
import com.ruralnative.handsy.di.UserRepo
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
    @TestDatabase
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
    }

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