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
import com.ruralnative.handsy.di.PhrasesDAO
import com.ruralnative.handsy.di.UserDAO
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
    @TestUserRepo
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @TestAlphabetRepo
    fun provideAlphabetRepository(): AlphabetLessonRepository {
        return AlphabetLessonRepository()
    }

    @Provides
    @TestPhrasesRepo
    fun providePhrasesRepository(): PhrasesLessonRepository {
        return PhrasesLessonRepository()
    }
}