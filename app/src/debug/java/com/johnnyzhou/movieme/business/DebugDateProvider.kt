package com.johnnyzhou.movieme.business

import java.time.LocalDate

abstract class DebugDateProvider: DateProvider {
    var overrideDate: LocalDate? = null
}