package com.johnnyzhou.movieme.network.response

import com.johnnyzhou.movieme.ui.person.Person

data class PersonListResponse(val people: List<Person>? = null)