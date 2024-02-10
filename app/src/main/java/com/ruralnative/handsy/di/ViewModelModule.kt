package com.ruralnative.handsy.di

import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import com.ruralnative.handsy.data.repository.UserRepository
import com.ruralnative.handsy.di.qualifiers.AlphabetDAO
import com.ruralnative.handsy.di.qualifiers.AlphabetRepo
import com.ruralnative.handsy.di.qualifiers.PhrasesDAO
import com.ruralnative.handsy.di.qualifiers.PhrasesRepo
import com.ruralnative.handsy.di.qualifiers.UserDAO
import com.ruralnative.handsy.di.qualifiers.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @UserRepo
    @ViewModelScoped
    fun provideUserRepository(
        @UserDAO dao: UserDao
    ): UserRepository {
        return UserRepository(dao)
    }

    @Provides
    @AlphabetRepo
    @ViewModelScoped
    fun provideAlphabetRepository(
        @AlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository {
        return AlphabetLessonRepository(dao)
    }

    @Provides
    @PhrasesRepo
    @ViewModelScoped
    fun providePhrasesRepository(
        @PhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository {
        return PhrasesLessonRepository(dao)
    }
}