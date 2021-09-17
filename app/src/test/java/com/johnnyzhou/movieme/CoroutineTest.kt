package com.johnnyzhou.movieme

import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class CoroutineTest {

    @Before
    fun setup() {

    }

    @Test
    fun test() = runBlocking {
        val job = GlobalScope.launch() {
            println("This is executed before the first delay")
            stallForTime()
            println("This is executed after the first delay")
        }

        GlobalScope.launch() {
            println("This is executed at the start of the second coroutine")
            job.cancelAndJoin()
            println("This is executed before the second delay")
            stallForTime()
            println("This is executed after the second delay")
        }

        println("This is executed immediately")    }
}

suspend fun stallForTime() {
    withContext(Dispatchers.Default) {
        delay(2000L)
    }
}