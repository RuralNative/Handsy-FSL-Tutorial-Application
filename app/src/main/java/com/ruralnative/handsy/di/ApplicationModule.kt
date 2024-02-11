package com.ruralnative.handsy.di

import android.content.Context
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
    @Singleton
    @UserRepo
    fun provideUserRepository(
        @UserDAO dao: UserDao
    ): UserRepository = UserRepository(dao)

    @Provides
    @Singleton
    @AlphabetRepo
    fun provideAlphabetRepository(
        @AlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository = AlphabetLessonRepository(dao)

    @Provides
    @Singleton
    @PhrasesRepo
    fun providePhrasesRepository(
        @PhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository = PhrasesLessonRepository(dao)
}

