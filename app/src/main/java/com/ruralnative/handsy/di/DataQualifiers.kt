package com.ruralnative.handsy.di

import javax.inject.Qualifier

class DataQualifiers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Database

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UserDao

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AlphabetDao

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PhrasesDao

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UserRepo

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AlphabetRepo

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PhrasesRepo
}