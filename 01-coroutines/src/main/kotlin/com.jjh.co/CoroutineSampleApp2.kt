package com.jjh.simple

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun executeSlowTask() {
        println("Starting Task")
        delay(5000) // simulates a long running task
        println("Done Task")
}

fun main() {
    println("Launching fire-and-forget task")
    GlobalScope.launch {
        executeSlowTask()
    }
    println("After launching coroutine")
    println("Waiting for task - press enter to continue:")
    readLine()
    println("Done")
}

