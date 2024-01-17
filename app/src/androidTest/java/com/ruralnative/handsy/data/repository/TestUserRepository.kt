package com.ruralnative.handsy.data.repository

import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.di.TestDatabase
import com.ruralnative.handsy.data.di.TestUserRepo
import com.ruralnative.handsy.di.UserRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TestUserRepository {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @TestDatabase lateinit var database: AppDatabase

    @Inject
    @TestUserRepo lateinit var repository: UserRepository

    @Before
    fun initDb() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }
}