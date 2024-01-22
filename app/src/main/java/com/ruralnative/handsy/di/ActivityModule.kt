package com.ruralnative.handsy.di

import com.ruralnative.handsy.data.repository.UserRepository
import com.ruralnative.handsy.di.qualifiers.EntryScreenViewModel
import com.ruralnative.handsy.di.qualifiers.IntroViewModel
import com.ruralnative.handsy.ui.entryUI.EntryViewModel
import com.ruralnative.handsy.ui.initialScreens.UserIntroViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @EntryScreenViewModel
    fun provideEntryViewModel(
        repository: UserRepository
    ): EntryViewModel {
        return EntryViewModel(repository)
    }

    @Provides
    @IntroViewModel
    fun provideUserIntroViewModel(
        repository: UserRepository
    ): UserIntroViewModel {
        return UserIntroViewModel(repository)
    }

}