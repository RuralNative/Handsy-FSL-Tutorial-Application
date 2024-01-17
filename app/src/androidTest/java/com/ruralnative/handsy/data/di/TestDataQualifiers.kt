package com.ruralnative.handsy.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestDatabase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestUserDAO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestAlphabetDAO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestPhrasesDAO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestUserRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestAlphabetRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestPhrasesRepo
    
