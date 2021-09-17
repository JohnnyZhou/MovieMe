package com.johnnyzhou.movieme.annotations

@Retention(AnnotationRetention.RUNTIME)
annotation class OverrideLocalDate(val year: Int, val month: Int, val day: Int)
