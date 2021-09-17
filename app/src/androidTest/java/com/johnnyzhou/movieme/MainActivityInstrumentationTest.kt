package com.johnnyzhou.movieme

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.johnnyzhou.movieme.annotations.OverrideLocalDate
import com.johnnyzhou.movieme.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentationTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val localDateRule = OverrideLocalDateRule(year = 3233, month = 10, day = 13)

//    @get:Rule
//    val chain: RuleChain = RuleChain.outerRule(localDateRule)

    @Test
    fun searchAvengerMovie() {
        val scenario = activityRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)
//        onView(withId(R.id.action_search)).perform(ViewActions.click())
    }
}
