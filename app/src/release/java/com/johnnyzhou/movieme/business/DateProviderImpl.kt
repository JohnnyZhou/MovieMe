package com.johnnyzhou.movieme.business

import java.time.LocalDate

class DateProviderImpl: DateProvider {
    override fun getCurrentDate(): LocalDate = LocalDate.now()
}