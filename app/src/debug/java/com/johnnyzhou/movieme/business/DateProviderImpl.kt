package com.johnnyzhou.movieme.business

import java.time.LocalDate

class DateProviderImpl: DebugDateProvider() {
    override fun getCurrentDate(): LocalDate = overrideDate ?: LocalDate.now()
}