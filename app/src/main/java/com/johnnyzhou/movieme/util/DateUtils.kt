package com.johnnyzhou.movieme.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils private constructor() {
    companion object {
        private var calendar: Calendar = Calendar.getInstance()
        private var dateFormat: SimpleDateFormat =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        fun convertDateFormat(date: String?): String {
            if (date == null) return "N/A"

            val sb = StringBuilder()

            try {
                calendar.time = dateFormat.parse(date) ?: Date()
                sb.append(calendar[Calendar.DAY_OF_MONTH])
                    .append(" ")
                    .append(
                        calendar.getDisplayName(
                            Calendar.MONTH,
                            Calendar.SHORT,
                            Locale.getDefault()
                        )
                    )
                    .append(" ")
                    .append(calendar[Calendar.YEAR])
            } catch (e: ParseException) {
                throw IllegalArgumentException()
            }

            return sb.toString()
        }
    }
}
