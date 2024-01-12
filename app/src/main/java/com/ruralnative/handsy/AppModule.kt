package com.ruralnative.handsy

import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAlphabetLessonRepository(dao: AlphabetLessonDao): AlphabetLessonRepository {
        return AlphabetLessonRepository(dao)
    }

}