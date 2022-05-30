package com.netsoft.android.authentication.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class) // Specifies the JUnit4 runner for Android tests
class ImplAuthManagerTest {

    // All tests cases cover ImplAuthManager
    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    private var myDataStore:MockDataStoreInstance = MockDataStoreInstance()
    private val repository: ImplAuthManager = ImplAuthManager(instrumentationContext)


    private val expectedUserPreferences: String = "Test"

    @Test
    fun repository_testFetchInitialPreferences() {
        myDataStore.testCoroutineScope.runBlockingTest {
            //TODO add asserts
        }
    }

    @Test
    fun repository_createUser() {
        myDataStore.testCoroutineScope.runBlockingTest {
            //TODO add asserts
        }
    }

}