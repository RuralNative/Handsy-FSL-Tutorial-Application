package com.ruralnative.handsy.di

import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import com.ruralnative.handsy.data.repository.UserRepository
import com.ruralnative.handsy.di.qualifiers.AlphabetDAO
import com.ruralnative.handsy.di.qualifiers.PhrasesDAO
import com.ruralnative.handsy.di.qualifiers.UserDAO
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
    @ViewModelScoped
    @TestUserRepo
    fun provideUserRepository(
        @TestUserDAO dao: UserDao
    ): UserRepository {
        return UserRepository(dao)
    }

    @Provides
    @ViewModelScoped
    @TestAlphabetRepo
    fun provideAlphabetRepository(
        @TestAlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository {
        return AlphabetLessonRepository(dao)
    }

    @Provides
    @ViewModelScoped
    @TestPhrasesRepo
    fun providePhrasesRepository(
        @TestPhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository {
        return PhrasesLessonRepository(dao)
    }
}