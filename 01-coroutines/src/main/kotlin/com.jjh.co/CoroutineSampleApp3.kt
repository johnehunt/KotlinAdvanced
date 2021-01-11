package com.jjh.simple

import kotlinx.coroutines.*

suspend fun executeSlowTaskWithResultAsync(): Deferred<Int> =
        GlobalScope.async {
            println("Starting Task")
            delay(5000)
            println("Done Task")
            return@async 42
        }

fun main() {
    println("Launching deferred result task")
    GlobalScope.launch {
        val result = executeSlowTaskWithResultAsync().await()
        println("result: $result")
    }
    println("After launching coroutine")
    println("Waiting for task - press enter to continue:")
    readLine()
    println("Done")
}