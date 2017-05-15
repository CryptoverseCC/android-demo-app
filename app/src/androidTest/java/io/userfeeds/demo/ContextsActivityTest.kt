package io.userfeeds.demo

import android.support.test.rule.ActivityTestRule
import io.userfeeds.demo.contexts.ContextsActivity
import org.junit.Rule
import org.junit.Test

class ContextsActivityTest {

    @Rule @JvmField
    val rule = ActivityTestRule<ContextsActivity>(ContextsActivity::class.java)

    @Test
    fun nothing() {
    }
}
