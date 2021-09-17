package com.johnnyzhou.movieme.business

import java.time.LocalDate

interface DateProvider {
    fun getCurrentDate(): LocalDate
}