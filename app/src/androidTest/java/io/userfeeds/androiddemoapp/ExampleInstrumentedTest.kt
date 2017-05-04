package io.userfeeds.androiddemoapp

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("io.userfeeds.androiddemoapp", appContext.packageName)
    }
}
