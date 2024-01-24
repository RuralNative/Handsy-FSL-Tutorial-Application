package com.ruralnative.handsy.di

import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import com.ruralnative.handsy.data.repository.UserRepository
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