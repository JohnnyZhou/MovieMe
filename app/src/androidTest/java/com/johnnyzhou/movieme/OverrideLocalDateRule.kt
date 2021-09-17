package com.johnnyzhou.movieme

import androidx.test.platform.app.InstrumentationRegistry
import com.johnnyzhou.movieme.business.DebugDateProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.time.LocalDate

class OverrideLocalDateRule(private val year: Int,
                            private val month: Int,
                            private val day: Int) : TestRule {

        override fun apply(base: Statement, description: Description): Statement {
//        description.annotations
//            .filterIsInstance(OverrideLocalDate::class.java)
//            .firstOrNull()?.run {
                ((InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as MovieMeApplication)
                    .appComponent.dateProvider as DebugDateProvider)
                    .overrideDate = LocalDate.of(year, month, day)
//            }

        return object : Statement() {
            override fun evaluate() {
                base.evaluate()
            }
        }
    }
}