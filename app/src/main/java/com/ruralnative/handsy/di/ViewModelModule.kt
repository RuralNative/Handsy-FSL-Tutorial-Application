package com.ruralnative.handsy.di

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
import com.ruralnative.handsy.di.qualifiers.EntryScreenViewModel
import com.ruralnative.handsy.di.qualifiers.IntroViewModel
import com.ruralnative.handsy.di.qualifiers.PhrasesDAO
import com.ruralnative.handsy.di.qualifiers.PhrasesRepo
import com.ruralnative.handsy.di.qualifiers.UserDAO
import com.ruralnative.handsy.di.qualifiers.UserRepo
import com.ruralnative.handsy.ui.entryUI.EntryViewModel
import com.ruralnative.handsy.ui.initialScreens.UserIntroViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    @AlphabetDAO
    fun provideAlphabetLessonDao(
        @Database database: AppDatabase
    ): AlphabetLessonDao = database.alphabetLessonDao()

    @Provides
    @PhrasesDAO
    fun providePhrasesLessonDao(
        @Database database: AppDatabase
    ): PhrasesLessonDao = database.phrasesLessonDao()

    @Provides
    @UserDAO
    fun provideUserDao(
        @Database database: AppDatabase
    ): UserDao = database.userDao()

    @Provides
    @AlphabetRepo
    fun provideAlphabetLessonRepository(
        @AlphabetDAO dao: AlphabetLessonDao
    ): AlphabetLessonRepository = AlphabetLessonRepository(dao)

    @Provides
    @PhrasesRepo
    fun providePhrasesLessonRepository(
        @PhrasesDAO dao: PhrasesLessonDao
    ): PhrasesLessonRepository = PhrasesLessonRepository(dao)

    @Provides
    @UserRepo
    fun provideUserRepository(
        @UserDAO dao: UserDao
    ): UserRepository = UserRepository(dao)

    @Provides
    @EntryScreenViewModel
    fun provideEntryViewModel(
        @UserRepo repository: UserRepository
    ): EntryViewModel {
        return EntryViewModel(repository)
    }

    @Provides
    @IntroViewModel
    fun provideUserIntroViewModel(
        @UserRepo repository: UserRepository
    ): UserIntroViewModel {
        return UserIntroViewModel(repository)
    }

}