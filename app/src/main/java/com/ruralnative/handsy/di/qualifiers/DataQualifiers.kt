package com.ruralnative.handsy.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Database

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserDAO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AlphabetDAO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PhrasesDAO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AlphabetRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PhrasesRepo
    
