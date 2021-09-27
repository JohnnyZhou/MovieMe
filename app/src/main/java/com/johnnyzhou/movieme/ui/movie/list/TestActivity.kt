package com.johnnyzhou.movieme.ui.movie.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.johnnyzhou.movieme.databinding.ActivityDetailMovieBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ActorScope
import kotlinx.coroutines.channels.actor
import kotlin.coroutines.Continuation

class TestActivity: AppCompatActivity() {

    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    suspend fun longFunction() {
        test()
    }

    suspend fun test() {
        repeat(30) {
            withContext(Dispatchers.Default) {
                println(it)
            }
        }
    }
}