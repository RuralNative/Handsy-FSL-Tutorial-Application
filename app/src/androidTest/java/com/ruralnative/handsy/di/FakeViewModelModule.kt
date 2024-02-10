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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [ViewModelComponent::class], replaces = [ViewModelModule::class])
object FakeViewModelModule {

    @Provides
    @TestUserRepo
    @ViewModelScoped
    fun provideUserRepository(
        @UserDAO dao: UserDao
    ): UserRepository = UserRepository(dao)

    @Provides
    @TestAlphabetRepo
    @ViewModelScoped
    fun provideAlphabetRepository(
        @AlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository = AlphabetLessonRepository(dao)

    @Provides
    @TestPhrasesRepo
    @ViewModelScoped
    fun providePhrasesRepository(
        @PhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository = PhrasesLessonRepository(dao)
}