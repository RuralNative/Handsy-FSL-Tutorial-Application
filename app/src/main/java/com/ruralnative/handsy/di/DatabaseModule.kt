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
import com.ruralnative.handsy.di.qualifiers.AlphabetDAO
import com.ruralnative.handsy.di.qualifiers.AlphabetRepo
import com.ruralnative.handsy.di.qualifiers.Database
import com.ruralnative.handsy.di.qualifiers.PhrasesDAO
import com.ruralnative.handsy.di.qualifiers.PhrasesRepo
import com.ruralnative.handsy.di.qualifiers.UserDAO
import com.ruralnative.handsy.di.qualifiers.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
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

